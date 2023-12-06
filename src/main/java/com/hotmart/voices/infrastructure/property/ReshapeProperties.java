package com.hotmart.voices.infrastructure.property;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "reshape")
@Configuration
@Data
public class ReshapeProperties {
    private String key;
    private String apiUrl;
    private String pathTranscription;
    private int speakers;
    private boolean profanityFilter;
}