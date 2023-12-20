package com.ITS.ITS.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.ITS.ITS.Entity.CustomUserDetailsService;


@Configuration
public class AppConfig {
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;

//	@Bean
//	public UserDetailsService userDetailsService(){
//	UserDetails u1 = User.builder().username("pradeeprathor3110@gmail.com").password(passwordEncoder().encode("abc")).build();
//	return new InMemoryUserDetailsManager(u1);
//	
//	
//	}
	
	
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
	 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService);
	}
	
}
