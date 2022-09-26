package com.ssafy.gumid207.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginDto {
	private String id;
	private String pass;
	
	@Builder
	public UserLoginDto(String id, String pass) {
		this.id = id;
		this.pass = pass;
	}


}
