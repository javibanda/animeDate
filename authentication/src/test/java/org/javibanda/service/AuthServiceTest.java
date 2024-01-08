package org.javibanda.service;

import org.javibanda.model.dto.AuthRequest;
import org.javibanda.model.dto.AuthResponse;
import org.javibanda.model.dto.UserDTO;
import org.javibanda.model.enums.AuthOperation;
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
        String pass = "contraseña";
        String hashed_password = BCrypt.hashpw(pass, BCrypt.gensalt());

        when(jwtUtil.generate("", "", "")).thenReturn("a");

        AuthResponse response = authService.getAuthResponse(userDTO(hashed_password), authRequest(pass), AuthOperation.LOGIN);
        assertNotNull(response);

    }

    private AuthRequest authRequest(String pass) {
        return new AuthRequest("as", "sf", pass, "rol");
    }

    private UserDTO userDTO(String codedPass) {
        return new UserDTO(UUID.randomUUID(), "d", "sf", codedPass, "rol");
    }
}