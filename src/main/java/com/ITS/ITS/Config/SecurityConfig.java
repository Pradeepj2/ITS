package com.ITS.ITS.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ITS.ITS.Security.JwtAuthanticationEntryPoint;
import com.ITS.ITS.Security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {
	@Autowired
	private JwtAuthanticationEntryPoint point;
	@Autowired
	private JwtAuthenticationFilter filter;

	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
		
		http.csrf(csrf -> csrf.disable()).cors(cors->cors.disable())
		.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/login").permitAll().requestMatchers("/home/getImage/**").permitAll()
				.anyRequest().authenticated()).exceptionHandling(er->er.authenticationEntryPoint(point))
		        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		 http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
//		http.csrf(csrf -> csrf.disable()).cors(cors->cors.disable())
//		.authorizeHttpRequests(auth -> auth.requestMatchers("/home/**")
//				.authenticated().requestMatchers("/auth/login").permitAll()
//				.anyRequest().authenticated()).exceptionHandling(er->er.authenticationEntryPoint(point))
//		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	}

}
