package org.javibanda.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequest {
    private String userName;
    private String email;
    private String password;
    private String role;
}
