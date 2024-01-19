package org.javibanda.service;

import lombok.val;
import org.javibanda.repository.AdminUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminUserServiceTest {


    @InjectMocks
    private AdminUserService adminUserService;

    @Mock
    private AdminUserRepository adminUserRepository;
    @Test
    public void should_return_true_when_isAdminUser() {
        when(adminUserRepository.existsById("email")).thenReturn(true);
        val isAdminUser = adminUserService.isAdminUser("email");

        assertTrue(isAdminUser);

    }
}