package com.spunit.UserManagement.controller;

import com.spunit.UserManagement.model.AuthUser;
import com.spunit.UserManagement.repository.UserRepository;
import com.spunit.UserManagement.request.LoginRequest;
import com.spunit.UserManagement.utility.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Endpoint for user registration
    @PostMapping("/register")
    public ResponseEntity<AuthUser> register(@RequestBody @Valid AuthUser authUser) {
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        AuthUser registeredAuthUser = userRepository.save(authUser);
        return ResponseEntity.status(201).body(registeredAuthUser);
    }

    // Endpoint for user login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        Authentication authentication = getAuthentication(loginRequest);
        if (authentication.isAuthenticated()) {
            String token = jwtUtil.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok(token);
        } else {
            throw new UsernameNotFoundException("INVALID_USER_REQUEST");
        }
    }

    private Authentication getAuthentication(LoginRequest loginRequest) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    }
}
