package com.hotmart.voices.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hotmart.voices.application.service.S3Service;
import com.hotmart.voices.application.service.TranscriptionService;
import com.hotmart.voices.domain.dto.TranscriptionCallbackDTO;
import com.hotmart.voices.domain.dto.TranscriptionRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@Tag(name = "Transcription API",description = "API for transcription text with Reshape")
@Slf4j
@AllArgsConstructor
public class TranscriptionController {

    private final TranscriptionService transcriptionService;
    private final S3Service s3Service;

    @GetMapping("/transcription/{id}")
    public ResponseEntity<String> transcriptionById(
            @Parameter(description = "Transcription id")
            @PathVariable(name = "id") String transcriptionId) {
        return ResponseEntity.ok(transcriptionService.getTranscription(transcriptionId));
    }

    @GetMapping("/transcription/{userCode}/{fileName}")
    public ResponseEntity<Void> callback(
            @Parameter(description = "Transcription id")
            @PathVariable(name = "userCode") String userCode,
            @Parameter(description = "File name")
            @PathVariable(name = "fileName") String fileName,
            @Parameter(description = "Callback data")
            @RequestBody TranscriptionCallbackDTO callbackDTO) {
        transcriptionService.callbackTranscription(callbackDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transcription")
    @Operation(summary = "Post an url video from Reshape")
    public ResponseEntity<String> transcription(
            @Parameter(description = "Request data for transcription")
            @RequestBody TranscriptionRequestDTO requestDTO) throws JsonProcessingException {
        return ResponseEntity.ok(transcriptionService.send(requestDTO));
    }
}
