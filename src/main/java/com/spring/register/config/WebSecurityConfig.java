package com.spring.register.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {
	private final UserDetailsService userService;
	
	@Bean
	public WebSecurityCustomizer configure() {
		return (web) -> web.ignoring().requestMatchers("/static/**", "/error"); 
	}
	
	@Bean
	public SecurityFilterChain fillterChain(HttpSecurity http) throws Exception{
		return http
				.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
						.requestMatchers("/login", "/signup", "/user").permitAll()
								.anyRequest().authenticated())
						
						.formLogin(formLogin -> {
							formLogin
							.loginPage("/login")
							.usernameParameter("email")
							.defaultSuccessUrl("/");
						})
						
						.logout(logout -> {
							logout
							.logoutSuccessUrl("/login")
							.invalidateHttpSession(true)
							.deleteCookies("JSESSIONID");
						})
						
						.csrf(csrf -> csrf.disable())
						
						.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http,
			BCryptPasswordEncoder bCryptPasswordEncoder,
			UserDetailsService userDetailsService) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userService)
				.passwordEncoder(bCryptPasswordEncoder)
				.and()
				.build();
	}
}
