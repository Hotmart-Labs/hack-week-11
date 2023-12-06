package com.hotmart.voices.application.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hotmart.voices.dto.TranscriptionCallbackDTO;
import com.hotmart.voices.infrastructure.property.VoicesProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
@Slf4j
public class S3Service {

    private final AmazonS3 amazonS3;
    private final VoicesProperties voicesProperties;

    public S3Service(AmazonS3 amazonS3, VoicesProperties voicesProperties) {
        this.amazonS3 = amazonS3;
        this.voicesProperties = voicesProperties;
    }

    public void upload(String paragraphTranscription, byte[] audioFile, TranscriptionCallbackDTO callbackDTO) {
        try {
            var in = new ByteArrayInputStream(paragraphTranscription.getBytes());
            var metadata = new ObjectMetadata();
            metadata.setContentLength(paragraphTranscription.getBytes().length);
            metadata.setContentType("txt");
            amazonS3.putObject(new PutObjectRequest("bucketName", "id_123", in, metadata));
        } catch (Exception e) {
            log.error("[S3Service] Error uploading file", e);
        }
    }
}
