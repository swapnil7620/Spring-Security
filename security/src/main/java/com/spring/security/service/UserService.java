package com.spring.security.service;

import com.spring.security.Entity.Users;
import com.spring.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
      private UserRepository userRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    public Users register(Users users){
        users.setPassword(encoder.encode(users.getPassword()));
       return   userRepository.save(users);
    }
}
