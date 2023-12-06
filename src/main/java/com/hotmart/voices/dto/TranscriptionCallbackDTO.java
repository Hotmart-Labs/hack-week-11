package com.hotmart.voices.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties
public class TranscriptionCallbackDTO {
    @JsonProperty("public_id")
    private String publicId;

    @JsonProperty("status")
    private String status;

    private String language;

    private Map<String,Object> metaData;

    @JsonProperty("stt_json_url")
    private String sttJsonUrl;

    @JsonProperty("transcript_url")
    private String transcriptUrl;

    @JsonProperty("captions_url")
    private String captionsUrl;

    @JsonProperty("plaintext_url")
    private String plaintextUrl;

}
