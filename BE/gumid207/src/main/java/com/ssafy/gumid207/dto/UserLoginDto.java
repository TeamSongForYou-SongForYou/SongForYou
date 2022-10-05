package com.ssafy.gumid207.dto;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ssafy.gumid207.entity.Authority;
import com.ssafy.gumid207.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto {
	private String id;
	private String pass;
	
	public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .id(id)
                .pass(passwordEncoder.encode(pass))
                .build();
    }
	
	public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(id, pass);
    }


}
