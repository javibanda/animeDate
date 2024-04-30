package org.javibanda.service;

import io.jsonwebtoken.Claims;
import org.javibanda.model.enums.TokenType;

public interface JwtUtil {

    void initKey();
    Claims getClaims(String token);
    String generate(String userId, String role, TokenType tokenType);
}
