package com.ssafy.gumid207.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.gumid207.dto.UserDto;
import com.ssafy.gumid207.dto.UserRegisterDto;
import com.ssafy.gumid207.jwt.SecurityUtil;
import com.ssafy.gumid207.res.ResponseUser;
import com.ssafy.gumid207.user.UserController;
import com.ssafy.gumid207.user.UserRepository;
import com.ssafy.gumid207.user.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/practice")
@RequiredArgsConstructor
@Slf4j
@Api(tags   = "연습 컨트롤러")
public class PracticeController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;
	
	@PostMapping("/practice1")
    @ApiOperation(value = "연습1", response = ResponseEntity.class)	
	public Boolean practice1(){
		System.out.println("user아이디: " + SecurityUtil.getCurrentMemberId());
		return false;
	}
	
	@GetMapping("/practice2")
    @ApiOperation(value = "연습2", response = ResponseEntity.class)
    public ResponseEntity<?> practice2(String email) throws Exception{
    	
        Boolean result = userService.checkEmail(email);
        System.out.println("결과는:" + result);
        String msg = result == true ? "사용 가능한 이메일입니다." : "이미 사용중인 이메일입니다.";
        
        
        return new ResponseEntity<>(ResponseUser.of(result, result, msg), HttpStatus.OK);
    }

}
