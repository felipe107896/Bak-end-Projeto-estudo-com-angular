package com.pessoa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pessoa.security.jwt.JwtConfigurer;
import com.pessoa.security.jwt.JwtTokenProvider;

import io.jsonwebtoken.JwtParser;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() throws Exception {
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
		return bcryptPasswordEncoder;
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
		return super.authenticationManager();
	}
	
	
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable()
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.authorizeRequests()
			.antMatchers("api/signin", "/api-docs/**","swagger-ui.htmml**").permitAll()
			.antMatchers("/api/**").authenticated()
			.antMatchers("/users").denyAll()
			.and()
			.apply(new JwtConfigurer(jwtTokenProvider));
		
	}

}
