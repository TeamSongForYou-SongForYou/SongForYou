package com.ssafy.gumid207.devutil;

import java.time.LocalDateTime;

import com.ssafy.gumid207.entity.User;

/**
 * 개발할 때 약간 인터페이스 느낌으로 씀
 *
 */
public class DevUtil {
	public static User getLoginUser() {
		User user = new User();
		user.setBirthday(1996);
		user.setGender("male");
		user.setId("anfidthtn");
		user.setNickName("무량소수");
		user.setPoint(1000);
		user.setPass("1234");
		user.setRegTime(LocalDateTime.now());
		
		return user;
	}
}
