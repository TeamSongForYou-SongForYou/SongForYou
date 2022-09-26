package com.ssafy.gumid207.config;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.ssafy.gumid207.jwt.JwtFilter;
import com.ssafy.gumid207.jwt.JwtUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{
    private final JwtUtil jwtUtil;

    @Override
    public void configure(HttpSecurity http){
        JwtFilter customJwtFilter = new JwtFilter(jwtUtil);
        http.addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
