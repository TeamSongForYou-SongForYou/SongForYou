package com.ssafy.gumid207.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import com.ssafy.gumid207.entity.File;

@Getter
@NoArgsConstructor
public class UserDto {
	private Long userSeq;
	private String nickName;
	private Integer point;
	private String id;
	private String pass;
	private File profileImgSeq;
	private Integer birthday;
	private String gender;
	private String fcmToken;
	private LocalDateTime regTime;

}
