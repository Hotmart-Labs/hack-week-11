package com.hotmart.voices.infrastructure.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "com.hotmart.voices.domain.gateway")
@Configuration
public class FeignConfig {}