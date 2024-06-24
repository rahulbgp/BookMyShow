package com.bookmyshow.controller;

import org.springframework.stereotype.Controller;

import com.bookmyshow.dto.LoginRequestDto;
import com.bookmyshow.dto.LoginResponseDto;
import com.bookmyshow.dto.ResponseStatus;
import com.bookmyshow.dto.SignUpRequestDto;
import com.bookmyshow.dto.SignUpResponseDto;
import com.bookmyshow.exception.UserNotFoundException;
import com.bookmyshow.models.User;
import com.bookmyshow.service.UserService;

@Controller
public class UserController {
	
	private  UserService userService;
	
	public UserController(UserService userService)  {
		this.userService = userService;
	}
	public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
		User user = userService.signUp(signUpRequestDto.getName(), signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
		
		SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
		signUpResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
		signUpResponseDto.setUserId(user.getId());
		
		return signUpResponseDto;
	}
	
	public LoginResponseDto login(LoginRequestDto loginRequestDto) {
		LoginResponseDto responseDto = new LoginResponseDto();
		try {
			User user = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
			responseDto.setResponseStatus(ResponseStatus.SUCCESS);
		} catch (Exception e) {
			responseDto.setResponseStatus(ResponseStatus.FAILURE);
		}
		return responseDto;
		
	}
}
