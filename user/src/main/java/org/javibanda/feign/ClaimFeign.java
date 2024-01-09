package org.javibanda.feign;

import org.javibanda.model.dto.ClaimDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-service")
public interface ClaimFeign {

    @GetMapping("/auth/claim")
    ClaimDTO getClaim(@RequestParam String token);

}
