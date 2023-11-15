package com.grocerystore.grocerystoregg.config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTValidator extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//we pass token in authorization header in JWTConstant class
		String jwt=request.getHeader(JWTConstant.JWT_HEADER);
		
		if(jwt!=null) {
			//Bearere(extract this) kdchc
			jwt=jwt.substring(7);
			try {
				SecretKey key=Keys.hmacShaKeyFor(JWTConstant.SECRET_KEY.getBytes());
				
				Claims claims=Jwts.parser().setSigningKey(key).build().parseClaimsJwt(jwt).getBody();
				
				String email=String.valueOf(claims.get("email"));
				
				String authorities=String.valueOf(claims.get("authorities"));
				
				List<GrantedAuthority> auths=AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
				Authentication authentication=new UsernamePasswordAuthenticationToken(email, null, auths);
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			}catch(Exception e) {
				
				throw new BadCredentialsException("invalid token.. from jwt validator");
				
			}
		}
		filterChain.doFilter(request, response);
		
	}
	
}
