package com.ssafy.gumid207.user;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.gumid207.dto.UserDto;
import com.ssafy.gumid207.dto.UserLoginDto;
import com.ssafy.gumid207.dto.UserRegisterDto;
import com.ssafy.gumid207.entity.User;
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
	@Autowired
	MailSendService mailService;
	@Autowired
	UserRepository userRepository;
	
	
	@PostMapping("/signUp")
    @ApiOperation(value = "회원가입", response = ResponseEntity.class)	
	public ResponseEntity<?> signUp(@RequestBody UserRegisterDto params) throws Exception{
		Boolean result = userService.saveUser(params);
		
		ResponseFrame<UserRegisterDto> resFrame = new ResponseFrame<>();
		resFrame.setCount(1);
		resFrame.setSuccess(true);
		resFrame.setMsg("요청한 회원의 정보를 반환합니다.");
		resFrame.setData(params);
		return new ResponseEntity<>(resFrame, HttpStatus.OK);
	}
	
	@GetMapping("/isUsedEmail")
    @ApiOperation(value = "이메일 중복 체크", response = ResponseEntity.class)
    public ResponseEntity<?> isUsedEmail(String email) throws Exception{
    	
        Boolean result = userService.checkEmail(email);
        System.out.println("결과는:" + result);
        String msg = result == true ? "사용 가능한 이메일입니다." : "이미 사용중인 이메일입니다.";
        
        
        return new ResponseEntity<>(ResponseFrame.of(result, msg), HttpStatus.OK);
    }
	
	@GetMapping("/isUsednickName")
    @ApiOperation(value = "닉네임 중복 체크", response = ResponseEntity.class)
    public ResponseEntity<?> isUsedNickName(String nickName) throws Exception{
    	
        Boolean result = userService.checkNickName(nickName);
        System.out.println("결과는:" + result);
        String msg = result == true ? "사용 가능한 닉네임입니다." : "이미 사용중인 닉네임입니다.";
        
        
        return new ResponseEntity<>(ResponseFrame.of(result, msg), HttpStatus.OK);
    }
	
	@GetMapping("/emailAuth")
    @ApiOperation(value = "이메일 인증", response = ResponseEntity.class)
    public ResponseEntity<?> emailAuth(String email) throws UnsupportedEncodingException{
    	String data = mailService.joinEmail(email);
        String msg = "이메일 인증번호 전송 완료";
        int count = 1;
        
        return new ResponseEntity<>(ResponseFrame.of(data, count, msg), HttpStatus.OK);
    }
	
	@PutMapping("/newPass")
	@ApiOperation(value = "비밀번호 찾기", response = ResponseEntity.class)
	public ResponseEntity<?> newPass(String email) throws UnsupportedEncodingException{
		Optional<User> user = userRepository.findByEmail(email);
		if(user.isEmpty()) {
			return new ResponseEntity<>(ResponseFrame.of(false, "가입되지 않은 이메일 입니다."), HttpStatus.OK);
		}
		else {
			String newPass = mailService.passEmail(email);
			System.out.println("newPass: " + newPass);
	        String msg = "새 비밀번호 전송 완료";
	        Boolean result = userService.modifyPass(email, newPass);
	        return new ResponseEntity<>(ResponseFrame.of(result, msg), HttpStatus.OK);
		}
	}
	
	@PostMapping("/login")
	@ApiOperation(value = "일반 로그인", response = ResponseEntity.class)
	public ResponseEntity<?> login(@RequestBody UserLoginDto params) {
		
		Boolean result = userService.loginUser(params);
		String msg;
		
		if(result) {
			msg = "로그인에 성공하였습니다.";
		}
		else {
			msg = "가입되지 않거나 틀린 비밀번호입니다.";
		}
		return new ResponseEntity<>(new ResponseFrame<>(result, params, 1, msg), HttpStatus.OK);
	}
}
