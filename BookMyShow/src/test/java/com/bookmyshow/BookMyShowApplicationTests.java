package com.bookmyshow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bookmyshow.controller.UserController;
import com.bookmyshow.dto.LoginRequestDto;
import com.bookmyshow.dto.LoginResponseDto;
import com.bookmyshow.dto.ResponseStatus;
import com.bookmyshow.dto.SignUpRequestDto;
import com.bookmyshow.dto.SignUpResponseDto;

@SpringBootTest
class BookMyShowApplicationTests {
	
	@Autowired
	private UserController userController;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void sampleTestCase() {
		SignUpRequestDto requestDto = new SignUpRequestDto();
		requestDto.setName("Rahul");
		requestDto.setEmail("rk381995@gmail.com");
		requestDto.setPassword("abcd@123");
		SignUpResponseDto responseDto = userController.signUp(requestDto);
		System.out.println(responseDto.getUserId());
	}

	@Test
	public void testLogin() {
		LoginRequestDto requestDto = new LoginRequestDto();
		requestDto.setEmail("rk381995@gmail.com");
		requestDto.setPassword("abcd@123");
		
		LoginResponseDto loginResponseDto = userController.login(requestDto);
		if(loginResponseDto.getResponseStatus().equals(ResponseStatus.SUCCESS)) {
			System.out.println("Login successfully");
		}
		else {
			System.out.println("Login Unsuccessfull");
		}
	}
}
