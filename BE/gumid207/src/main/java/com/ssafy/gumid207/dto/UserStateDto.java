package com.ssafy.gumid207.dto;

import com.ssafy.gumid207.entity.File;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserStateDto {
	private Long userSeq;
	private String id;
	private String nickName;
	private String email;
	private File profileImgSeq;
	private Integer birthday;
	private String gender;

}
