package com.ssafy.gumid207.user;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.gumid207.dto.TokenDto;
import com.ssafy.gumid207.dto.UserLoginDto;
import com.ssafy.gumid207.dto.UserRegisterDto;
import com.ssafy.gumid207.entity.RefreshToken;
import com.ssafy.gumid207.entity.User;
import com.ssafy.gumid207.jwt.JwtUtil;
import com.ssafy.gumid207.jwt.RefreshTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	@Autowired
	private final UserRepository userRepository;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JwtUtil jwtUtil;
	private final RefreshTokenRepository refreshTokenRepository;
	private final PasswordEncoder passwordEncoder;

	
	@Transactional
	//회원가입
	public boolean saveUser(UserRegisterDto params) {
		try {
			
			User user = params.toUser(passwordEncoder);
			userRepository.save(user);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	@Transactional
	//이메일 중복 검사
	public Boolean checkEmail(String email) {
		Optional<User> result = userRepository.findByEmail(email);
		if(result.isPresent()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	@Transactional
	//이메일 중복 검사
	public Boolean checkNickName(String nickName) {
		Optional<User> result = userRepository.findByNickName(nickName);
		if(result.isPresent()) {
			return false;
		}
		else {
			return true;
		}      
	

	}
	
	@Transactional
	//비밀번호 변경
	public Boolean modifyPass(String email, String newPass) {
		Optional<User> result = userRepository.findByEmail(email);
		System.out.println("new: " + newPass);
		if(result.isPresent()) {
			return true;
		}
		else {
			result.get().modifyPass(newPass);
			return false;
		}
	}
	
	public TokenDto loginUser(UserLoginDto params) {
		
		// 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = params.toAuthentication();
        System.out.println("1");
        System.out.println("authenticationToken: " + authenticationToken);
        
     // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        System.out.println("authentication: " + authentication);
        System.out.println("2");
        
     // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = jwtUtil.generateTokenDto(authentication);
        System.out.println("3");
		
        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 5. 토큰 발급
        return tokenDto;
		
	}
}
