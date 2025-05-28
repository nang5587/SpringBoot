package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import edu.pnu.config.filter.JWTAuthenticationFilter;
import edu.pnu.config.filter.JWTAuthorizationFilter;
import edu.pnu.persistence.MemberRepository;

@Configuration
public class SecurityConfig {
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		// CsrfFilter 제거
		http.csrf(csrf -> csrf.disable());
		
		// AuthorizationFilter 추가
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll());
		
		http.formLogin(frmLogin -> frmLogin.disable());
		http.httpBasic(basic -> basic.disable());
		
		http.sessionManagement(sm -> sm.sessionCreationPolicy(
										SessionCreationPolicy.STATELESS));
		
		http.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()));
		
		http.addFilterBefore(new JWTAuthorizationFilter(memberRepo), AuthorizationFilter.class);
		
		return http.build();
	}
}
