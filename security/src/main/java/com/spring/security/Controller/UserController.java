package com.spring.security.Controller;

import com.spring.security.DTO.LoginRequest;
import com.spring.security.Entity.Users;
import com.spring.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/register")
    public Users register(@RequestBody Users users) {
        return userService.register(users);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest login){
//        System.out.println(users);
//        return  "Success";

         return  userService.verify(login);
    }

}
