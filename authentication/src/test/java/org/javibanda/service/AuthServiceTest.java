package org.javibanda.service;

import org.javibanda.model.dto.AuthRequest;
import org.javibanda.model.dto.AuthResponse;
import org.javibanda.model.dto.User;
import org.javibanda.model.enums.AuthOperation;
import org.javibanda.model.enums.TokenType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private JwtUtil jwtUtil;

    @Test
    public void register() {
        String pass = "password";
        String hashed_password = BCrypt.hashpw(pass, BCrypt.gensalt());

        when(jwtUtil.generate("", "", TokenType.REFRESH)).thenReturn("a");

        AuthResponse response = authService.getAuthResponse(userDTO(hashed_password), authRequest(pass), AuthOperation.LOGIN, false);
        assertNotNull(response);

    }

    private AuthRequest authRequest(String pass) {
        return new AuthRequest("as", "sf", pass);
    }

    private User userDTO(String codedPass) {
        return new User(UUID.randomUUID(), "d", "sf", codedPass, "rol");
    }
}