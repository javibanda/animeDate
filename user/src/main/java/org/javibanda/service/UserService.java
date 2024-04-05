package org.javibanda.service;

import org.javibanda.model.dto.ClaimDTO;
import org.javibanda.model.entity.user.User;

import java.util.UUID;

public interface UserService {

    User save(User user);

    User get(String email);

    String getName(UUID id);

    ClaimDTO getUserFromToken(String token);

}
