package com.ITS.ITS.Controller;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ITS.ITS.Entity.JwtRequest;
import com.ITS.ITS.Entity.JwtResponse;
import com.ITS.ITS.Security.JwtHelper;
import com.ITS.ITS.Services.UsersServices;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UsersServices usersServices;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private JwtHelper helper;

	private Logger logger = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
 
		this.doAuthenticate(request.getEmail(), request.getPassword());

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
	
		
		String token = this.helper.generateToken(userDetails);
		
		String emailString = userDetails.getUsername();
		
		     Random random = new Random();
	        // Generate a random 6-digit number
	        int count = random.nextInt(999999 - 100000 + 1) + 100000;

		token += "rjglei%ddspradeepgh#ffh344dg22765wwdvb" + count;
		usersServices.setUniqueCode(count , emailString);

		JwtResponse response = new JwtResponse.Builder().jwtToken(token).username(emailString).build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private void doAuthenticate(String email, String password) {
		System.err.println("outside the doauthanticate");

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			System.err.println("inside the doauthanticate");
			manager.authenticate(authentication);
			

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}

	}

	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials Invalid !!";
	}

}