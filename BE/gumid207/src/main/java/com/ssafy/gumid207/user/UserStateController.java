package com.ssafy.gumid207.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.gumid207.dto.UserStateDto;
import com.ssafy.gumid207.entity.User;
import com.ssafy.gumid207.jwt.SecurityUtil;
import com.ssafy.gumid207.res.ResponseUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user-state")
@RequiredArgsConstructor
@Slf4j
@Api(tags   = "유저 상태정보 컨트롤러")
public class UserStateController {
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/information")
	@ApiOperation(value = "유저상태정보", response = ResponseEntity.class)
	public ResponseEntity<?> userState() {
		String id = SecurityUtil.getCurrentMemberId();
		UserStateDto userStateDto = new UserStateDto();
		Optional<User> user = userRepository.findById(id);
		userStateDto.setUserSeq(user.get().getUserSeq());
		userStateDto.setId(user.get().getId());
		userStateDto.setNickName(user.get().getNickName());
		userStateDto.setEmail(user.get().getEmail());
		userStateDto.setProfileImgSeq(user.get().getProfileImgSeq());
		userStateDto.setBirthday(user.get().getBirthday());
		userStateDto.setGender(user.get().getGender());
		
		return new ResponseEntity<>(ResponseUser.of(true, userStateDto, "사용자 정보입니다."), HttpStatus.OK);
		
	}

}
