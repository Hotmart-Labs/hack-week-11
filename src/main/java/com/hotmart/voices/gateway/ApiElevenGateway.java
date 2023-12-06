package com.hotmart.voices.gateway;

import com.hotmart.voices.gateway.dto.ReshapeRequestDTO;
import com.hotmart.voices.gateway.dto.ReshapeResponseDTO;
import com.hotmart.voices.gateway.dto.ReshapeTextResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "api-eleven", url = "${feign.client.api-eleven}")
public interface ApiElevenGateway {

  @PostMapping(path = "/api/v1/transcriptions/")
  ReshapeResponseDTO createTranscription(@RequestHeader(name = "Authorization") String apiToken,
                                         @RequestBody ReshapeRequestDTO transcriptionRequestDTO);

  @GetMapping(path = "/api/v1/transcriptions/{transcriptionId}/transcript")
  ReshapeTextResponseDTO getTranscription(@RequestHeader(name = "Authorization") String apiToken,
                                          @PathVariable(value = "transcriptionId") String transcriptionId);
  @PostMapping(path = "/api/v1/transcriptions/")
  byte[] genareteAudio(String paragraphsStr);
}