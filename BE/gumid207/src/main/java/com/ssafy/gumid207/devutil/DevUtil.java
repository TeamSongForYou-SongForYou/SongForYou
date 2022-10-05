package com.ssafy.gumid207.devutil;

import java.time.LocalDateTime;

import com.ssafy.gumid207.dto.UserDto;
import com.ssafy.gumid207.entity.User;
import com.ssafy.gumid207.user.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * 개발할 때 약간 인터페이스 느낌으로 씀
 *
 */
@RequiredArgsConstructor
public class DevUtil {
	private final UserRepository userRepo;
	
	public UserDto getLoginUser() {
		User user = null;
		try {
			user = userRepo.findById(3l).get();
		}
		catch (Exception e) {			
			user = new User();
			user.setBirthday(1996);
			user.setGender("male");
			user.setId("anfidthtn");
			user.setNickName("무량소수");
			user.setPoint(1000);
			user.setPass("1234");
			user.setRegTime(LocalDateTime.now());
		}

		return UserDto.of(user);
	}

//	public static UserDto getLoginUser() {
//		Authentication autentication = SecurityContextHolder.getContext().getAuthentication();
//		UserDto tokenUser = (UserDto) autentication.getPrincipal();
//		return tokenUser;
//	}
}
