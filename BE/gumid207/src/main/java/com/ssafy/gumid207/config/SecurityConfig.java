package com.ssafy.gumid207.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.gumid207.jwt.CustomUserDetailsService;
import com.ssafy.gumid207.jwt.JwtAccessDeniedHandler;
import com.ssafy.gumid207.jwt.JwtAuthenticationEntryPoint;
import com.ssafy.gumid207.jwt.JwtUtil;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity(debug = false)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	private final JwtUtil jwtUtil;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CustomUserDetailsService customUserDetailsService;
///oauth2/authorization/google
//	private final UserRepository userRepo;
//	private final JwtUtilsService jwtUtilService;
	private final ObjectMapper mapper;
	// private final CustomOAuth2UserService oAuth2UserService;
//	private final OAuth2SuccessHandler successHandler;

//	private final GoogleTokenValidate googleTokenValidate;
//	private final NaverTokenValidate naverTokenValidate;
//	private final KaKaoTokenValiate kakaoTokenValidate;

	// 패스워드 암호화에 사용
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//			throws Exception {
//
//		return authenticationConfiguration.getAuthenticationManager();
//	}
//
	@Bean
	public WebSecurityCustomizer WebSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/test", "/test/**", "/images/**", "/swagger-ui/**",
				"/swagger-resources/**", "/v2/api-docs");

	}
	

//
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager)
//			throws Exception {
//		http.cors().configurationSource(corsConfigurationSource());
//		// http.cors().disable();// cors 필터 사용안함
//		http.httpBasic().disable(); // 헤더에 username,password 로그인 사용 불가
//		http.csrf().disable(); // csrf 보안 사용 안함
//		http.anonymous().disable(); // 익명 사용자 허용 x
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 스프링 시큐리티 컨텍스트 홀더 세션 사용 안함
//
//		// oath 사용한다고 설정,
//		// http.oauth2Login().successHandler(successHandler).userInfoEndpoint()
//		// .userService(oAuth2UserService);
//
////		http.addFilterBefore(new JwtAuthFilter(jwtUtilService, userRepo, mapper),
////				UsernamePasswordAuthenticationFilter.class);
//
////		http.addFilterBefore(new CustomOAuthLoginValidateFilter(googleTokenValidate, naverTokenValidate,
////				kakaoTokenValidate, successHandler), JwtAuthFilter.class);
//
////		http.authorizeHttpRequests((authz) -> {
////			authz.antMatchers("/user/profile").hasRole(Role.TEMP.toString());
////			authz.antMatchers("/customer-center/manager/**").hasRole(Role.MANAGER.toString());
////			authz.antMatchers("/**").hasRole(Role.USER.toString());
////			
////		});
//		http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
//
//			@Override
//			public void handle(HttpServletRequest request, HttpServletResponse response,
//					AccessDeniedException accessDeniedException) throws IOException, ServletException {
//
//				response.getWriter().println(String.format("%s -- %s", "실패", accessDeniedException.getMessage()));
//
//			}
//		}).authenticationEntryPoint(new AuthenticationEntryPoint() {
//
//			@Override
//			public void commence(HttpServletRequest request, HttpServletResponse response,
//					AuthenticationException authException) throws IOException, ServletException {
//
//				System.out.println(authException);
//
//			}
//		});
//
//		// 어뗀티 케이션 디나이 핸들러는 따로 처리하고 있음
//
//		return http.build();
//	}
//
//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//
//		configuration.addAllowedOriginPattern("*");
//		configuration.addAllowedHeader("*");
//		configuration.addAllowedMethod("*");
//		configuration.setAllowCredentials(true);
//
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
	    DaoAuthenticationProvider bean = new DaoAuthenticationProvider();
	    bean.setHideUserNotFoundExceptions(false);
	    bean.setUserDetailsService(customUserDetailsService);
	    bean.setPasswordEncoder(passwordEncoder());
	    
	    return bean;
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception{
			// CSRF 설정 Disable
			http.csrf().disable()

            // exception handling 할 때 우리가 만든 클래스를 추가
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)


            // 시큐리티는 기본적으로 세션을 사용
            // 여기서는 세션을 사용하지 않기 때문에 세션 설정을 Stateless 로 설정
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            // 로그인, 회원가입 API 는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll 설정
            .and()
            .authorizeRequests()
            .antMatchers("/user/**").permitAll()
            .anyRequest().authenticated()   // 나머지 API 는 전부 인증 필요

            // JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
            .and()
                .apply(new JwtSecurityConfig(jwtUtil));
    }
}

