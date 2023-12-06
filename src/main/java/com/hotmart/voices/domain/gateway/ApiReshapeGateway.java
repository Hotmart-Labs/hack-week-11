package com.hotmart.voices.domain.gateway;

import com.hotmart.voices.domain.dto.ReshapeRequestDTO;
import com.hotmart.voices.domain.dto.ReshapeResponseDTO;
import com.hotmart.voices.domain.dto.ReshapeTextResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "api-reshape", url = "${feign.client.api-reshape}")
public interface ApiReshapeGateway {

  @PostMapping(path = "/api/v1/transcriptions/")
  ReshapeResponseDTO createTranscription(@RequestHeader(name = "Authorization") String apiToken,
                                         @RequestBody ReshapeRequestDTO transcriptionRequestDTO);

  @GetMapping(path = "/api/v1/transcriptions/{transcriptionId}/transcript")
  ReshapeTextResponseDTO getTranscription(@RequestHeader(name = "Authorization") String apiToken,
                                          @PathVariable(value = "transcriptionId") String transcriptionId);
}