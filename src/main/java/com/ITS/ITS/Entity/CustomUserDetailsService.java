package com.ITS.ITS.Entity;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ITS.ITS.Dao.UsersDao;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersDao userRepo;

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Jciumt user = this.userRepo.findByUserName(username)
//				.orElseThrow(() -> new Apiexception("Invalid UserName"));
//		return  (UserDetails) user;
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = userRepo.findByEmail(email);
		return new org.springframework.security.core.userdetails.User(user.getEmail(), passwordEncoder().encode(user.getPassword_hash()),
				new ArrayList<>());
	}
	
}

