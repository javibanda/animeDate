package org.javibanda.service;

import lombok.RequiredArgsConstructor;
import org.javibanda.repository.AdminUserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final AdminUserRepository repository;

    public boolean isAdminUser(String email){
        return repository.existsById(email);
    }

}
