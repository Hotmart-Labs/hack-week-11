package com.hotmart.voices.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "com.hotmart.voices.gateway")
@Configuration
public class FeignConfig {}