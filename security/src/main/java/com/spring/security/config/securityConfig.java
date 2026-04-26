package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception{
       // To disable default spring security
        httpSecurity.csrf(customizer -> customizer.disable());
        // To authorize the user each time
        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        // form login  -> for browser
        httpSecurity.formLogin(Customizer.withDefaults());
        // httpBasic -> for postman
        httpSecurity.httpBasic(Customizer.withDefaults());
        return  httpSecurity.build();

    }
}
