package com.ssafy.gumid207.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.gumid207.dto.UserRegisterDto;
import com.ssafy.gumid207.res.ResponseFrame;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
@Api(tags   = "유저 컨트롤러")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/signUp")
    @ApiOperation(value = "회원가입", response = boolean.class)	
	public ResponseEntity<?> signUp(@RequestBody UserRegisterDto params) {
		boolean result = userService.saveUser(params);
		
		ResponseFrame<UserRegisterDto> resFrame = new ResponseFrame<>();
		resFrame.setCount(1);
		resFrame.setSuccess(true);
		resFrame.setMsg("요청한 회원의 정보를 반환합니다.");
		resFrame.setData(params);
		return new ResponseEntity<>(resFrame, HttpStatus.OK);
	}

}
