package com.spring.security.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/greet")
    public String greet(HttpServletRequest HSR){
        return  "Welcome to our page  " + HSR.getSession().getId();
    }
}
