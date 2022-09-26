package com.ssafy.gumid207.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter{
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final JwtUtil jwtUtil;

    private String resolveToken(HttpServletRequest request){
        String token = request.getHeader(AUTHORIZATION_HEADER);
        if(StringUtils.hasText(token) && token.startsWith(BEARER_PREFIX)){
            return token.substring(7);
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                                    throws IOException, ServletException{
        String bearerToken = this.resolveToken(request);

        if(StringUtils.hasText(bearerToken) && jwtUtil.validateToken(bearerToken)){
            Authentication authentication = jwtUtil.getAuthentication(bearerToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
