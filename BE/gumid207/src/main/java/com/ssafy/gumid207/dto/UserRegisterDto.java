package com.ssafy.gumid207.dto;

import java.time.LocalDateTime;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ssafy.gumid207.entity.File;
import com.ssafy.gumid207.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {
	private String nickName;
	private String email;
	private String id;
	private String pass;
	private Integer birthday;
	private String gender;
	
	public User toUser(PasswordEncoder passwordEncoder) {
		return User.builder()
				.nickName(nickName)
				.email(email)
				.id(id)
				.pass(passwordEncoder.encode(pass))
				.birthday(birthday)
				.gender(gender)
				.build();
	}
	
	public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(id, pass);
    }

}
