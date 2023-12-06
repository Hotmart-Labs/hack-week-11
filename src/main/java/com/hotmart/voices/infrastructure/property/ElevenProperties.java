package com.hotmart.voices.infrastructure.property;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "eleven")
@Configuration
@Data
public class ElevenProperties {
    private String key;
    private String apiUrl;
    private String path;
    private String voiceId;
    private String model;
    private Double stability;
    private Double similarityBoost;
}