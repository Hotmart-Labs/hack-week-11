package com.hotmart.voices.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class ReshapeRequestDTO {
    private String url;
    private String language;
    private String name;
    private String callbackUrl;
    private int speakers;
    @JsonProperty("profanity_filter")
    private boolean profanityFilter;
    private Map<String, Object> metadata;
}