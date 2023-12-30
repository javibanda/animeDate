package org.javibanda.service;

import lombok.RequiredArgsConstructor;
import org.javibanda.model.entity.User;
import org.javibanda.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user){
        if (userExist(user)){
            return null;
        }
        user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }

    public User get(User user){
        return userRepository.getUserByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    private boolean userExist(User user){
        return userRepository.countByEmailOrUserName(user.getEmail(), user.getUserName()) != 0;
    }
}
