package com.ssafy.gumid207.jwt;

import java.util.Collections;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.gumid207.entity.User;
import com.ssafy.gumid207.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		return userRepository.findById(id).map(this::createUserDetails)
				.orElseThrow(() -> new UsernameNotFoundException("가입되지 않거나 틀린 비밀번호입니다."));
	}

	// DB 에 User 값이 존재한다면 UserDetails 객체로 만들어서 리턴
	private UserDetails createUserDetails(User user) {
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getAuthority().toString());

		return new org.springframework.security.core.userdetails.User(String.valueOf(user.getId()), user.getPass(),
				Collections.singleton(grantedAuthority));
	}
}
