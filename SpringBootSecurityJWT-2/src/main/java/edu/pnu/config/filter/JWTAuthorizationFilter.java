package edu.pnu.config.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter{
	private final MemberRepository memberRepo;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(token == null || token.startsWith("Beared ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String new_token = token.replace("Bearer ", "");
		String username = JWT.require(Algorithm.HMAC256("edu.pnu.jwt")).build().verify(new_token).getClaim("username").asString();
		
		Optional<Member> opt = memberRepo.findById(username);
		if(!opt.isPresent()) {
			filterChain.doFilter(request, response);
			return;
		}
		
		Member member = opt.get();
		
		User user = new User(member.getUsername(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));
		
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		filterChain.doFilter(request, response);
	}
}
