package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.bean.MyUser;
import com.example.demo.mapper.UserMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = mapper.findByUserName(username);
        return User.builder()
        .username(username)
        .password(user.getPassword())
        .build();
    }

    public void save(MyUser user){
       mapper.insert(user.getUsername(), user.getPassword());
    }

}
