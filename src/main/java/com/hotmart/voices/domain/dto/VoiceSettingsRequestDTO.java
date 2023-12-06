package com.hotmart.voices.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class VoiceSettingsRequestDTO {
    private Double stability;
    @JsonProperty("similarity_boost")
    private Double similarityBoost;
}