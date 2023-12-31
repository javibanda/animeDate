package org.javibanda.controller;

import lombok.AllArgsConstructor;

import org.javibanda.model.dto.AuthRequest;
import org.javibanda.model.dto.AuthResponse;
import org.javibanda.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        AuthResponse authResponse = authService.register(request);
        if (authResponse != null){
            return ResponseEntity.ok(authService.register(request));
        }else {
            return ResponseEntity.status(HttpStatus.FOUND).build();
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        AuthResponse authResponse = authService.login(request);
        if (authResponse != null){
            return ResponseEntity.ok(authService.register(request));
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}