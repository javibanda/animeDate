package org.javibanda.service;

import lombok.RequiredArgsConstructor;
import org.javibanda.feign.ClaimFeign;
import org.javibanda.model.dto.ClaimDTO;
import org.javibanda.model.enums.Role;
import org.javibanda.model.entity.user.User;
import org.javibanda.repository.ProfileRepository;
import org.javibanda.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final AdminUserService adminUserService;
    private final ClaimFeign claimFeign;

    public User save(User user){
        if (userExist(user)){
            return null;
        }
        user.setId(UUID.randomUUID());
        user.setRole(getRole(user.getEmail()).name());
        return userRepository.save(user);
    }

    public User get(String email){
        return userRepository.getUserByEmail(email);
    }

    public ClaimDTO getUserFromToken(String token){
        ClaimDTO claim = claimFeign.getClaim(token);
        claim.setId(profileRepository.findProfileId(claim.getId()));
        return claim;
    }

    private Role getRole(String email){
        if (adminUserService.isAdminUser(email)){
            return Role.ADMIN;
        }
        return Role.USER;
    }

    private boolean userExist(User user){
        return userRepository.countByEmailOrUserName(user.getEmail(), user.getUserName()) != 0;
    }
}
