package org.javibanda.service;

import org.javibanda.model.dto.AuthRequest;
import org.javibanda.model.dto.AuthResponse;
import org.javibanda.model.dto.ClaimDTO;
import org.javibanda.model.dto.User;
import org.javibanda.model.enums.AuthOperation;

public interface AuthService {
    AuthResponse register(AuthRequest request);

    AuthResponse login(AuthRequest request);
    ClaimDTO getClaims(String token);
    AuthResponse getAuthResponse(User user, AuthRequest request, AuthOperation authOperation);
}
