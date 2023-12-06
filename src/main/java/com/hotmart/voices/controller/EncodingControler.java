package com.hotmart.voices.controller;

import com.hotmart.voices.application.service.EncodingService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/v1")
@Tag(name = "Encoding API",description = "API for encoding files")
@Slf4j
public class EncodingControler {

    @Autowired
    private EncodingService encodingService;

    @GetMapping("/encoding/{id}")
    public ResponseEntity encodingById(
            @Parameter(description = "encoding id")
            @PathVariable(name = "id") String transcriptionId) throws IOException {

        String caminhoVideo = "123";
        String caminhoAudio = "2345";
        encodingService.encodingFile(caminhoVideo, caminhoAudio);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
