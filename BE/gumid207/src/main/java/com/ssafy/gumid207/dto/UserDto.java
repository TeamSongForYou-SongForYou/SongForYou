package com.ssafy.gumid207.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.gumid207.entity.User;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 직렬화 기능을 가진 User클래스
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

	private Long userSeq;

	@ApiParam(value = "유저 이름")
	private String userNickName;

	@ApiParam(value = "유저 포인트")
	private Integer userPoint;

	@ApiParam(value = "유저 아이디")
	private String userId;

	@ApiParam(value = "유저 비밀번호")
	private String userPass;

	@ApiParam(value = "유저 이메일")
	private String userEmail;

	@ApiParam(value = "유저 생년")
	private Integer userBirthday;

	@ApiParam(value = "유저 성별")
	private String userGender;

	@ApiParam(value = "유저 fcm 토큰")
	private String userFcmToken;

	@ApiParam(value = "등록 시간. (yyyy-MM-dd 00:00:00)")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime userRegTime;

	public static UserDto of(User user) {
		if (user == null) {
			return null;
		}
		return new UserDtoBuilder() //
				.userSeq(user.getUserSeq()) //
				.userPoint(user.getPoint()) //
				.userNickName(user.getNickName()) //
				.userId(user.getId()) //
				.userPass(user.getPass()) //
				.userEmail(user.getEmail()) //
				.userBirthday(user.getBirthday()) //
				.userGender(user.getGender()) //
				.userFcmToken(user.getFcmToken()) //
				.userRegTime(user.getRegTime()) //
				.build();
	}

}