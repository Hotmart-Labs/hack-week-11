package com.hotmart.voices.properties;


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
}