package com.hotmart.voices.infrastructure.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@Getter
//@Setter
@Component
@ConfigurationProperties(prefix = "voices")
public class VoicesProperties {
    private String bucket;
}
