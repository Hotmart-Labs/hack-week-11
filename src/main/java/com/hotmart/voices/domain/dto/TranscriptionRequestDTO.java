package com.hotmart.voices.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TranscriptionRequestDTO {
    private String url;
    private String language;
}