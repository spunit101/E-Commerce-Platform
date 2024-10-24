package com.spunit.UserManagement.controller;

import com.spunit.UserManagement.model.AuthUser;
import com.spunit.UserManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserProfileController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public ResponseEntity<AuthUser> getProfile(@AuthenticationPrincipal AuthUser authUser) {
        return ResponseEntity.ok(authUser);
    }

    @PutMapping("/profile")
    public ResponseEntity<AuthUser> updateProfile(@AuthenticationPrincipal AuthUser authUser, @RequestBody AuthUser updatedAuthUser) {
        // Update user details logic
        return ResponseEntity.ok(userRepository.save(updatedAuthUser));
    }
}

