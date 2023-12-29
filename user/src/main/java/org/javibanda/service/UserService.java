package org.javibanda.service;

import lombok.RequiredArgsConstructor;
import org.javibanda.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService {

    public UserDTO save(UserDTO userDTO){
        String userId = String.valueOf(new Date().getTime());
        userDTO.setId(userId);
        userDTO.setRole("USER");
        return userDTO;
    }
}
