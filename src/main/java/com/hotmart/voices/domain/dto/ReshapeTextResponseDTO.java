package com.hotmart.voices.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@JsonIgnoreProperties
public class ReshapeTextResponseDTO {
    private String language;
    private List<ReshapeParagraphResponseDTO> paragraphs;
}