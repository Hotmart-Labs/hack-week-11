package com.hotmart.voices.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hotmart.voices.domain.dto.*;
import com.hotmart.voices.domain.gateway.ApiElevenGateway;
import com.hotmart.voices.domain.gateway.ApiReshapeGateway;
import com.hotmart.voices.infrastructure.property.ElevenProperties;
import com.hotmart.voices.infrastructure.property.ReshapeProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TranscriptionService {

    public static final String HOTMART_VOICES_HOST = "https://voices.buildstaging.com";
    public static final String S3_BUCKET = "https://staging-hotmart.s3.amazonaws.com/hackweek11/video/";

    public static final String PATH_API = "v1/transcription";


    private final ReshapeProperties reshapeProperties;
    private final ElevenProperties elevenProperties;

    private final ApiReshapeGateway apiReshapeGateway;
    private final ApiElevenGateway apiElevenGateway;
    private final S3Service s3Service;

    public String send(TranscriptionRequestDTO requestDTO) throws JsonProcessingException {
        var fileName = requestDTO.getUrl().replace(S3_BUCKET, StringUtils.EMPTY);
        var userCode = UUID.randomUUID().toString();
        var callbackUrl = String.join("/", HOTMART_VOICES_HOST, PATH_API, userCode, fileName);

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("userCode", userCode);
        metadata.put("fileName", fileName);
        metadata.put("fileUrl", requestDTO.getUrl());

        ReshapeRequestDTO request = ReshapeRequestDTO.builder()
                .url(requestDTO.getUrl())
                .language(requestDTO.getLanguage())
                .name(fileName)
                .speakers(reshapeProperties.getSpeakers())
                .profanityFilter(reshapeProperties.isProfanityFilter())
                .callbackUrl(callbackUrl)
                .metadata(metadata)
                .build();

        String apiToken = "Token " + reshapeProperties.getKey();
        ReshapeResponseDTO transcriptionResponse = apiReshapeGateway.createTranscription(apiToken, request);
        log.info("Result {}", transcriptionResponse);
        return transcriptionResponse.getPublicId();
    }

    public void callbackTranscription(TranscriptionCallbackDTO requestDTO, String userCode, String fileName) {
        if(Objects.nonNull(requestDTO.getPublicId())) {
            log.info("Callback transcription with public id {}", requestDTO.getPublicId());
            var paragraphsStr = this.getTranscription(requestDTO.getPublicId());
            log.info("Paragraph transcription: {}", paragraphsStr);
            var elevenRequestDTO = ElevenRequestDTO.builder()
                    .text(paragraphsStr)
                    .modelId(elevenProperties.getModel())
                    .voiceSettingsDTO(VoiceSettingsRequestDTO.builder()
                            .stability(elevenProperties.getStability())
                            .similarityBoost(elevenProperties.getSimilarityBoost())
                            .build())
                    .build();

            byte[] audioFile = apiElevenGateway.createAudio(
                    elevenProperties.getKey(), elevenProperties.getVoiceId(), elevenRequestDTO);
            s3Service.upload(audioFile, userCode, fileName);
        }
    }


    public String getTranscription(String transcriptionId) {
        String apiToken = "Token " + reshapeProperties.getKey();
        ReshapeTextResponseDTO transcriptionResponse = apiReshapeGateway.getTranscription(apiToken, transcriptionId);
        log.info("Result {}", transcriptionResponse);
        List<ReshapeParagraphResponseDTO> paragraphs = transcriptionResponse.getParagraphs();
        return paragraphs.stream()
                .map(ReshapeParagraphResponseDTO::getText)
                .collect(Collectors.joining(StringUtils.SPACE));
    }
}
