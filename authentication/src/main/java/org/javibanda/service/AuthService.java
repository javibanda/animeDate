package org.javibanda.service;

import lombok.RequiredArgsConstructor;
import org.javibanda.feign.UserFeign;
import org.javibanda.model.dto.AuthRequest;
import org.javibanda.model.dto.AuthResponse;
import org.javibanda.model.dto.UserDTO;
import org.javibanda.model.enums.AuthOperation;
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
        return getAuthResponse(registeredUser, request, AuthOperation.REGISTER);
    }

    public AuthResponse login(AuthRequest request){
        UserDTO userDTO = userFeign.getUser(request.getEmail());
        return getAuthResponse(userDTO, request, AuthOperation.LOGIN);
    }

    public AuthResponse getAuthResponse(UserDTO userDTO, AuthRequest request, AuthOperation authOperation){
        if (userDTO == null){
            return null;
        }
        if (!checkPassword(request.getPassword(), userDTO.getPassword(), authOperation)){
            return null;
        }
        String accessToken = jwtUtil.generate(userDTO.getId().toString(), userDTO.getRole(), "ACCESS");
        String refreshToken = jwtUtil.generate(userDTO.getId().toString(), userDTO.getRole(), "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }

    private boolean checkPassword(String typedPass, String encodePass, AuthOperation authOperation){
        if (authOperation == AuthOperation.REGISTER){
            return true;
        }else {
            return BCrypt.checkpw(typedPass, encodePass);
        }
    }
}
