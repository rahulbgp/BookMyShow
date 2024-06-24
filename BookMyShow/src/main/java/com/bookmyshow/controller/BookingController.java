package com.bookmyshow.controller;

import org.springframework.stereotype.Controller;

import com.bookmyshow.dto.BookingRequestDto;
import com.bookmyshow.dto.BookingResponseDto;
import com.bookmyshow.dto.ResponseStatus;
import com.bookmyshow.exception.ShowNotFoundException;
import com.bookmyshow.exception.UserNotFoundException;
import com.bookmyshow.models.Booking;
import com.bookmyshow.service.BookingService;

@Controller
public class BookingController {
	private BookingService bookingService;
	
	
	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
		
	}
	
	public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto) throws UserNotFoundException, ShowNotFoundException {
		
		BookingResponseDto bookingResponseDto = new BookingResponseDto();
		try {
			
		Booking booking = bookingService.createBooking(bookingRequestDto.getUserId(),
				bookingRequestDto.getShowSeatId(), bookingRequestDto.getShowId());
		bookingResponseDto.setBookingId(booking.getId());
		bookingResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
		
		}
		catch(Exception e) {
			bookingResponseDto.setResponseStatus(ResponseStatus.FAILURE);
		}
		return bookingResponseDto;
	}
}
