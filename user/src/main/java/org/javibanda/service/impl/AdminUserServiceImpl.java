package org.javibanda.service.impl;

import lombok.RequiredArgsConstructor;
import org.javibanda.repository.AdminUserRepository;
import org.javibanda.service.AdminUserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminUserRepository repository;

    @Override
    public boolean isAdminUser(String email){
        return repository.existsById(email);
    }

}
