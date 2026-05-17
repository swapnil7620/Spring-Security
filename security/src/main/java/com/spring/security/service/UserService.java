package com.spring.security.service;

import com.spring.security.DTO.LoginRequest;
import com.spring.security.Entity.Users;
import com.spring.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public Users register(Users users) {
        users.setPassword(encoder.encode(users.getPassword()));
        return userRepository.save(users);
    }

    public String verify(LoginRequest users) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getName(), users.getPassword()));

        if (authentication.isAuthenticated()) {
            return  jwtService.generateToken(users.getName());
        }
        else {
            return "fail";
        }
    }
}