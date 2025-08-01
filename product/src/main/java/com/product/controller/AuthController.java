package com.product.controller;

import com.product.dto.AuthResponse;
import com.product.entity.User;
import com.product.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return ResponseEntity.ok(authService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody User user) {
        // Get token
        String token = authService.loginUser(user.getUsername(), user.getPassword());

        // Get role
        String role = authService.getUserRole(user.getUsername());

        // Wrap in AuthResponse
        AuthResponse response = new AuthResponse(token, role);

        return ResponseEntity.ok(response);
    }
}
