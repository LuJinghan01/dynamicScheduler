package com.example.demo.bean;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUser implements UserDetails {
    private Integer id;
    private String username;
    private String password;

    public MyUser(){}

    public MyUser(String userName, String userPassword){
        this.username = userName;
        this.password = userPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return username;
    }

    public void setPassword(String password){
        this.password = password;
    }


    
}
