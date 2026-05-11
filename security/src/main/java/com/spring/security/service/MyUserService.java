package com.spring.security.service;


import com.spring.security.Entity.UserPrinciple;
import com.spring.security.Entity.Users;
import com.spring.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserService  implements UserDetailsService {


    private final UserRepository userRepository;

    public MyUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Users user = userRepository.findByName(name) ;

        if(user==null){
            System.out.println("user not found");
            throw  UsernameNotFoundException.fromUsername("user not found");
        }
        return new UserPrinciple(user);
    }
}
