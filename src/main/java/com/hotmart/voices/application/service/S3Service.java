package com.hotmart.voices.application.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hotmart.voices.infrastructure.property.VoicesProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class S3Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(S3Service.class);

    private final AmazonS3 amazonS3;
    private final VoicesProperties voicesProperties;

    public S3Service(AmazonS3 amazonS3, VoicesProperties voicesProperties) {
        this.amazonS3 = amazonS3;
        this.voicesProperties = voicesProperties;
    }

    public void upload(String input) {
        try {
            var in = new ByteArrayInputStream(input.getBytes());
            var metadata = new ObjectMetadata();
            metadata.setContentLength(input.getBytes().length);
            metadata.setContentType("txt");
            amazonS3.putObject(new PutObjectRequest("bucketName", "id_123", in, metadata));
        } catch (Exception e) {
            LOGGER.error("[S3Service] Error uploading file", e);
        }
    }
}
