package org.javibanda.service;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.javibanda.feign.UserFeign;
import org.javibanda.model.dto.AuthRequest;
import org.javibanda.model.dto.AuthResponse;
import org.javibanda.model.dto.ClaimDTO;
import org.javibanda.model.dto.User;
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
        User registeredUser = restTemplate.postForObject("http://user-service/users", request, User.class);
        return getAuthResponse(registeredUser, request, AuthOperation.REGISTER);
    }

    public AuthResponse login(AuthRequest request){
        User user = userFeign.getUser(request.getEmail());
        return getAuthResponse(user, request, AuthOperation.LOGIN);
    }

    public ClaimDTO getClaims(String token){
        Claims claims = jwtUtil.getClaims(token);
        return new ClaimDTO(claims.get("id").toString(), claims.get("role").toString());
    }

    public AuthResponse getAuthResponse(User user, AuthRequest request, AuthOperation authOperation){
        if (user == null){
            return null;
        }
        if (!checkPassword(request.getPassword(), user.getPassword(), authOperation)){
            return null;
        }
        String accessToken = jwtUtil.generate(user.getId().toString(), user.getRole(), "ACCESS");
        String refreshToken = jwtUtil.generate(user.getId().toString(), user.getRole(), "REFRESH");

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
