package com.bookmyshow.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bookmyshow.exception.UserNotFoundException;
import com.bookmyshow.models.User;
import com.bookmyshow.repository.UserRepository;

public class UserService {
	
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	public User signUp(String name, String email, String password) {
		/*
		 * 1.check if user already exists with given mail id
		 * 2.If yes ask the user to login
		 * 3.if not create new user object wit given details
		 * 4. save to DB
		 */
		
		
		
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(bCryptPasswordEncoder.encode(password));
		return userRepository.save(user);
		
	}
	
	public User login(String email, String password) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if(optionalUser.isEmpty()) {
			throw new UserNotFoundException("User with email " + email + " not found");
			
		}
		User user = optionalUser.get();
		//compare the password
		if(bCryptPasswordEncoder.matches(password, user.getPassword())) {
			// login successfull
			return user;
		}
		throw new RuntimeException("Password mismatch");
		
	}
	
	
	

}
