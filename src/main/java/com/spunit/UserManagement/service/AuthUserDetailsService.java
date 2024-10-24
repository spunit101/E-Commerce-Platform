package com.spunit.UserManagement.service;

import com.spunit.UserManagement.model.AuthUser;
import com.spunit.UserManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthUser> userDetail = userRepository.findByUsername(username); // Assuming 'email' is used as username
        return userDetail.map(AuthUserInfoDetailsService::new).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

}
