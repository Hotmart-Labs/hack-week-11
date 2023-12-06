package com.hotmart.voices.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hotmart.voices.dto.TranscriptionCallbackDTO;
import com.hotmart.voices.dto.TranscriptionRequestDTO;
import com.hotmart.voices.gateway.dto.ReshapeParagraphResponseDTO;
import com.hotmart.voices.gateway.dto.ReshapeRequestDTO;
import com.hotmart.voices.gateway.dto.ReshapeResponseDTO;
import com.hotmart.voices.gateway.dto.ReshapeTextResponseDTO;
import com.hotmart.voices.gateway.ApiReshapeGateway;
import com.hotmart.voices.properties.ReshapeProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TranscriptionService {

    public static final String HOTMART_VOICES_HOST = "https://hotmart-voices.buildstaging.com";
    public static final String S3_BUCKET = "https://staging-hotmart.s3.amazonaws.com/hackweek11/audio/";

    public static final String VERSION_API = "v1";


    private final ReshapeProperties reshapeProperties;
    private final ApiReshapeGateway apiReshapeGateway;

    public String send(TranscriptionRequestDTO requestDTO) throws JsonProcessingException {
        var fileName = requestDTO.getUrl().replace(S3_BUCKET, StringUtils.EMPTY);
        var callbackUrl = String.join("/", HOTMART_VOICES_HOST, VERSION_API, requestDTO.getUserCode(), fileName);

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("userCode", requestDTO.getUserCode());
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

    public String callbackTranscription(TranscriptionCallbackDTO requestDTO) {
        if(Objects.nonNull(requestDTO.getPublicId())) {
            log.info("Callback transcription with public id {}", requestDTO.getPublicId());
            var paragraphsStr = this.getTranscription(requestDTO.getPublicId());
            log.info("Paragraph transcription: {}", paragraphsStr);
            return paragraphsStr;
        }
        return null;
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
