package org.javibanda.service;

import io.jsonwebtoken.Claims;

public interface JwtUtil {

    void initKey();
    Claims getClaims(String token);
    String generate(String userId, String role, String tokenType);
}
