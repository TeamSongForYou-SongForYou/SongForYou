package com.ssafy.gumid207.dto;

import java.time.LocalDateTime;

import com.ssafy.gumid207.entity.File;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Getter
@NoArgsConstructor
public class UserRegisterDto {
	private String nickName;
	private String id;
	private String pass;
	private File profileImgSeq;
	private Integer birthday;
	private String gender;
	private LocalDateTime regTime;
	
	@Builder
	public UserRegisterDto(String nickName, String id, String pass, File profileImgSeq,
			Integer birthday, String gender, LocalDateTime regTime) {
		this.nickName = nickName;
		this.id = id;
		this.pass = pass;
		this.profileImgSeq = profileImgSeq;
		this.birthday = birthday;
		this.gender = gender;
		this.regTime = regTime;
	}

}
