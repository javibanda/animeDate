package org.javibanda.service;

import lombok.RequiredArgsConstructor;
import org.javibanda.model.dto.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;

    public UserDTO save(UserDTO userDTO){
        String userId = String.valueOf(new Date().getTime());
        userDTO.setId(userId);
        userDTO.setRole("USER");
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userDTO;
    }
}
