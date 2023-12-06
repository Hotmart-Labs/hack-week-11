package com.hotmart.voices.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class ElevenRequestDTO {
    @JsonProperty("model_id")
    private String modelId;
    private String text;
    @JsonProperty("voice_settings")
    private VoiceSettingsRequestDTO voiceSettingsDTO;
}