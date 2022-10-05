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

import com.ssafy.gumid207.dto.TokenDto;
import com.ssafy.gumid207.dto.TokenRequestDto;
import com.ssafy.gumid207.dto.UserDto;
import com.ssafy.gumid207.dto.UserLoginDto;
import com.ssafy.gumid207.dto.UserRegisterDto;
import com.ssafy.gumid207.entity.User;
import com.ssafy.gumid207.res.ResponseFrame;
import com.ssafy.gumid207.res.ResponseUser;

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
		String msg = "회원가입 성공";
		params.setPass("private information");
	
		return new ResponseEntity<>(ResponseUser.of(result, params, msg), HttpStatus.OK);
	}
	
	@GetMapping("/isUsedEmail")
    @ApiOperation(value = "이메일 중복 체크", response = ResponseEntity.class)
    public ResponseEntity<?> isUsedEmail(String email) throws Exception{
    	
        Boolean result = userService.checkEmail(email);
        System.out.println("결과는:" + result);
        String msg = result == true ? "사용 가능한 이메일입니다." : "이미 사용중인 이메일입니다.";
        
        
        return new ResponseEntity<>(ResponseUser.of(result, result, msg), HttpStatus.OK);
    }
	
	@GetMapping("/isUsednickName")
    @ApiOperation(value = "닉네임 중복 체크", response = ResponseEntity.class)
    public ResponseEntity<?> isUsedNickName(String nickName) throws Exception{
    	
        Boolean result = userService.checkNickName(nickName);
        System.out.println("결과는:" + result);
        String msg = result == true ? "사용 가능한 닉네임입니다." : "이미 사용중인 닉네임입니다.";
        
        
        return new ResponseEntity<>(ResponseUser.of(result, result, msg), HttpStatus.OK);
    }
	
	@GetMapping("/emailAuth")
    @ApiOperation(value = "이메일 인증", response = ResponseEntity.class)
    public ResponseEntity<?> emailAuth(String email) throws UnsupportedEncodingException{
    	String data = mailService.joinEmail(email);
        String msg = "이메일 인증번호 전송 완료";
        int count = 1;
        
        return new ResponseEntity<>(ResponseUser.of(true, data, msg), HttpStatus.OK);
    }
	
	@PutMapping("/newPass")
	@ApiOperation(value = "비밀번호 찾기", response = ResponseEntity.class)
	public ResponseEntity<?> newPass(String email) throws UnsupportedEncodingException{
		Optional<User> user = userRepository.findByEmail(email);
		if(user.isPresent()) {
			String newPass = mailService.passEmail(email);
			System.out.println("newPass: " + newPass);
	        String msg = "새 비밀번호 전송 완료";
	        Boolean result = userService.modifyPass(email, newPass);
	        return new ResponseEntity<>(ResponseUser.of(result, "pass", msg), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(ResponseUser.of(false, "failure", "가입되지 않은 이메일 입니다."), HttpStatus.OK);
		}
	}
	
	@PostMapping("/login")
	@ApiOperation(value = "일반 로그인", response = ResponseEntity.class)
	public ResponseEntity<?> login(@RequestBody UserLoginDto params) {
		
		TokenDto tokenDto = userService.loginUser(params);

		return new ResponseEntity<>(ResponseUser.of(true, tokenDto, "로그인 성공했습니다."), HttpStatus.OK);
	}
	
	@PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(userService.reissue(tokenRequestDto));
    }
}
