package org.javibanda.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.javibanda.model.entity.User;
import org.javibanda.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/get")
    public ResponseEntity<User> get(@RequestParam String email){
        return ResponseEntity.ok(userService.get(email));
    }

    @GetMapping("/secured")
    public ResponseEntity<User> securedEndpoint(@RequestHeader("Authorization") String token) {
        val user = userService.getUserFromToken(token);
        return ResponseEntity.ok(user);
    }


}
