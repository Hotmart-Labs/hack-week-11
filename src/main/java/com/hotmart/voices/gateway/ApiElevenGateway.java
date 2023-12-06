package com.hotmart.voices.gateway;

import com.hotmart.voices.gateway.dto.ElevenRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "api-eleven", url = "${feign.client.api-eleven}")
public interface ApiElevenGateway {

  @PostMapping(path = "/v1/text-to-speech/{voiceId}")
  byte[] createAudio(@RequestHeader(name = "xi-api-key") String apiToken,
                     @PathVariable(name = "voiceId") String voiceId,
                     @RequestBody ElevenRequestDTO elevenRequestDTO);
}