package edu.pnu.config.filter;

import java.io.IOException;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.pnu.domain.Member;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private final AuthenticationManager authenticationManager;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Member member = mapper.readValue(request.getInputStream(), Member.class);
			Authentication authenToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
			
			return authenticationManager.authenticate(authenToken);
		}
		catch(Exception e) {
			log.info(e.getMessage());
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
		
		return null;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User user = (User)authResult.getPrincipal();
		
		String token = JWT.create()
				.withExpiresAt(new Date(System.currentTimeMillis() +1000*60*100))
				.withClaim("username", user.getUsername())
				.sign(Algorithm.HMAC256("edu.pnu.jwt"));
		response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		response.setStatus(HttpStatus.OK.value());
	}
}
