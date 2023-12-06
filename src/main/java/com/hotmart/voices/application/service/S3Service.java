package com.hotmart.voices.application.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hotmart.voices.domain.dto.TranscriptionCallbackDTO;
import com.hotmart.voices.infrastructure.property.VoicesProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

@Service
@Slf4j
@AllArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;
    private final VoicesProperties voicesProperties;

    public void upload(String paragraphTranscription, byte[] audioFile, TranscriptionCallbackDTO callbackDTO) {
        try {
            var in = new ByteArrayInputStream(paragraphTranscription.getBytes());
            var metadata = new ObjectMetadata();
            metadata.setContentLength(paragraphTranscription.getBytes().length);
            metadata.setContentType("txt");
            amazonS3.putObject(new PutObjectRequest(voicesProperties.getBucket(), "id_123", in, metadata));
        } catch (Exception e) {
            log.error("[S3Service] Error uploading file", e);
        }
    }

    public String download(String id, String bucket, String path) {
        try {
            var response = amazonS3.getObject(bucket, path+id);
            InputStream objectData = response.getObjectContent();
            Files.copy(objectData, new File("/tmp/"+id).toPath()); //location to local path
            objectData.close();
        } catch (AmazonS3Exception e) {
            log.error("[S3Service] Object not found", e);
        } catch (Exception ex) {
            log.error("[S3Service] Error to process s3 object", ex);
        }
        return "ok";
    }
}
