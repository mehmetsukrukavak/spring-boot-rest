package com.mehmetsukrukavak.spring_boot_rest.controller;

import com.mehmetsukrukavak.spring_boot_rest.model.User;
import com.mehmetsukrukavak.spring_boot_rest.service.JwtService;
import com.mehmetsukrukavak.spring_boot_rest.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.saveUser(user);

    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

        if (authentication.isAuthenticated()) return jwtService.generateToken(user.getUsername());

        return "Invalid username or password";
    }
}
