package org.javibanda.service;

import lombok.RequiredArgsConstructor;
import org.javibanda.feign.UserFeign;
import org.javibanda.model.dto.AuthRequest;
import org.javibanda.model.dto.AuthResponse;
import org.javibanda.model.dto.UserDTO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final RestTemplate restTemplate;
    private final UserFeign userFeign;
    private final JwtUtil jwtUtil;

    public AuthResponse register(AuthRequest request) {
        request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        UserDTO registeredUser = restTemplate.postForObject("http://user-service/users", request, UserDTO.class);
        return getAuthResponseRegister(registeredUser);
    }

    public AuthResponse login(AuthRequest request){
        UserDTO userDTO = userFeign.getUser(request);
        return getAuthResponseLogin(userDTO, request);
    }

    private AuthResponse getAuthResponseLogin(UserDTO userDTO, AuthRequest request){
        if (userDTO == null){
            return null;
        }
        if (BCrypt.checkpw(request.getPassword(), userDTO.getPassword())){
            return null;
        }
        String accessToken = jwtUtil.generate(userDTO.getId().toString(), userDTO.getRole(), "ACCESS");
        String refreshToken = jwtUtil.generate(userDTO.getId().toString(), userDTO.getRole(), "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }

    private AuthResponse getAuthResponseRegister(UserDTO userDTO){
        if (userDTO == null){
            return null;
        }

        String accessToken = jwtUtil.generate(userDTO.getId().toString(), userDTO.getRole(), "ACCESS");
        String refreshToken = jwtUtil.generate(userDTO.getId().toString(), userDTO.getRole(), "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }
}
