package com.hotmart.voices.gateway.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@JsonIgnoreProperties
public class ReshapeResponseDTO {
    @JsonProperty("public_id")
    private String publicId;
    private String status;
    private String language;
    private String callbackUrl;
    private int speakers;
    @JsonProperty("stt_json_url")
    private String sttJsonUrl;
    @JsonProperty("transcript_url")
    private String transcriptUrl;
    @JsonProperty("captions_url")
    private String captionsUrl;
    @JsonProperty("plaintext_url")
    private String plaintextUrl;
}