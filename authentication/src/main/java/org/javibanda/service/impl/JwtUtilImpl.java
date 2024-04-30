package org.javibanda.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.javibanda.model.enums.TokenType;
import org.javibanda.service.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtUtilImpl implements JwtUtil {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private String expiration;
    private Key key;

    @PostConstruct
    @Override
    public void initKey() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    @Override
    public String generate(String userId, String role, TokenType tokenType) {
        Map<String, String> claims = Map.of("id", userId, "role", role);
        long expMillis = 0;

        switch (tokenType){
            case ACCESS:
                expMillis = Long.parseLong(expiration) * 1000;
                break;
            case REFRESH:
                expMillis = Long.parseLong(expiration) * 1000 * 5;
                break;
            case MOBILE:
                expMillis = Long.parseLong(expiration) * 1000 * 1000000;
                break;

        }
        final Date now = new Date();
        final Date exp = new Date(now.getTime() + expMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(claims.get("id"))
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key)
                .compact();
    }


}
