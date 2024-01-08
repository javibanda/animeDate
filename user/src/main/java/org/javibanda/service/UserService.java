package org.javibanda.service;

import lombok.RequiredArgsConstructor;
import org.javibanda.model.Role;
import org.javibanda.model.entity.User;
import org.javibanda.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AdminUserService adminUserService;

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
