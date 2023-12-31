package org.javibanda.feign;

import org.javibanda.model.dto.AuthRequest;
import org.javibanda.model.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service")
public interface UserFeign {

    @GetMapping("/users/get")
    UserDTO getUser(AuthRequest request);

}
