package com.ssafy.gumid207.user;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.gumid207.dto.UserLoginDto;
import com.ssafy.gumid207.dto.UserRegisterDto;
import com.ssafy.gumid207.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	@Autowired
	private final UserRepository userRepository;
	
	@Transactional
	//회원가입
	public boolean saveUser(UserRegisterDto params) {
		try {
			
			userRepository.save(params.toEntity());
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
		if(result.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Transactional
	//이메일 중복 검사
	public Boolean checkNickName(String nickName) {
		Optional<User> result = userRepository.findByNickName(nickName);
		if(result.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	

	}
	
	@Transactional
	//비밀번호 변경
	public Boolean modifyPass(String email, String newPass) {
		Optional<User> result = userRepository.findByEmail(email);
		System.out.println("new: " + newPass);
		if(result.isEmpty()) {
			return false;
		}
		else {
			result.get().modifyPass(newPass);
			return true;
		}
	}
	
	public Boolean loginUser(UserLoginDto params) {
		Optional<User> result = userRepository.findByIdAndPass(params.getId(), params.getPass());
		if(result.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
}
