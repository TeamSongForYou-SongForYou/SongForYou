package com.ssafy.gumid207.dto;

import java.time.LocalDateTime;

import com.ssafy.gumid207.entity.File;
import com.ssafy.gumid207.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Getter
@NoArgsConstructor
public class UserRegisterDto {
	private String nickName;
	private String email;
	private String id;
	private String pass;
	private Integer birthday;
	private String gender;
	
	public User toEntity() {
		return User.builder()
				.nickName(nickName)
				.email(email)
				.id(id)
				.pass(pass)
				.birthday(birthday)
				.gender(gender)
				.build();
	}

}
