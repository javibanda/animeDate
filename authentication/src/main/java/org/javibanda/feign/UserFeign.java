package org.javibanda.feign;

import org.javibanda.model.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserFeign {
    @GetMapping("/users/get")
    User getUser(@RequestParam String email);
}