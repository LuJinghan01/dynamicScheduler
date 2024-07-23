package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.example.demo.bean.MyUser;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserDetailsServiceImpl;
import com.example.demo.webtoken.JwtService;
import com.example.demo.webtoken.LoginForm;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Controller;
import org.springframework.security.authentication.AuthenticationManager;


@Controller
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper mapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsServiceImpl myUserDetailService;
    @NacosValue(value = "${cleanup.test}",autoRefreshed = true)
    private String test;



    @GetMapping("/user/home")
    public ResponseEntity<String> getUserHome() {
        System.out.println(test);
        return ResponseEntity.ok(test);
    }

    @GetMapping("/login")
    public String handleLogin() {
        return "custom_login";
    }

    @PostMapping("/register")
    @ResponseBody
    public String createUser(@RequestBody MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        mapper.insert(user.getUsername(),user.getPassword());
        return "successfully registered";
    }  

    @PostMapping("/authenticate")
    @ResponseBody
    public String authenticateAndGetToken(@RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginForm.username(), loginForm.password()
        ));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(myUserDetailService.loadUserByUsername(loginForm.username()));
        } else {
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }

}
