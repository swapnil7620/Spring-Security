package com.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
@Configuration
@EnableWebSecurity
public class securityConfig {

    public final UserDetailsService userDetailsService;

    public securityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
/*
        // To disable default spring security
        httpSecurity.csrf(customizer -> customizer.disable());
        // To authorize the user each time
        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        // form login  -> for browser
//        httpSecurity.formLogin(Customizer.withDefaults());
        // httpBasic -> for postman
        httpSecurity.httpBasic(Customizer.withDefaults());
        // To eliminate the session id but for each request you need to login
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();

*/
        // short way


        return httpSecurity.csrf(customizer -> customizer.disable()).
                authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults()).
                sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

/*
// still its manual but for understanding purpose we done
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1 = User
                .withDefaultPasswordEncoder().
                 username("swapnil").
                 password("pass").
                  roles("USER")
                .build();
        UserDetails user2 = User
                .withDefaultPasswordEncoder().
                username("Niraj").
                password("pass").
                roles("ADMIN")
                .build();
        return  new InMemoryUserDetailsManager(user1,user2);

    }

 */

    // Real time

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(10));
        return provider;
    }

}
