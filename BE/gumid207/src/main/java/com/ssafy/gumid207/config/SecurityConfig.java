package com.ssafy.gumid207.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
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
	@Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                    .antMatchers("/user/**").permitAll()
                    .antMatchers("/manage/**", "/dashboard/**").hasRole("MANAGER")
                    .antMatchers("/kickboard/**").hasAnyRole("USER", "MANAGER")
            .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
            .and()
                .exceptionHandling()
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    .accessDeniedHandler(jwtAccessDeniedHandler)
            .and()
                .apply(new JwtSecurityConfig(jwtUtil));
    }
}

