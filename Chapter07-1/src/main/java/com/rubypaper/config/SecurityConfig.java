package com.rubypaper.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//	로그인을 강제하지 않음
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(security -> security
				.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
				.requestMatchers("/admin/**").hasAnyRole("ADMIN")
				.anyRequest().permitAll());
		http.csrf(cf -> cf.disable()); // 사이트간 요청 위조
		
		//  UsernamePasswordAuthenticationFilter 이거 쓰려고
		http.formLogin(form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/loginSuccess", true));
		
		http.exceptionHandling(ex -> ex.accessDeniedPage("/accessDenied"));
		
		http.logout(logout -> logout
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/login"));
		
		return http.build();
	}
	
//	@Autowired
//	public void authenticate(AuthenticationManagerBuilder auth) throws Exception{
//		auth.inMemoryAuthentication().withUser("member").password("{noop}abcd").roles("MEMBER"); // {noop} : 비밀번호 암호화 X
//		auth.inMemoryAuthentication().withUser("manager").password("{noop}abcd").roles("MANAGER");
//		auth.inMemoryAuthentication().withUser("admin").password("{noop}abcd").roles("ADMIN");
//	}
}
