package com.hotmart.voices.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class ReshapeParagraphResponseDTO {
    private String text;
}
