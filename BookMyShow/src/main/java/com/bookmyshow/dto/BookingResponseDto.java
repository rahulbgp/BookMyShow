package com.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponseDto {
	private Long bookingId;
	private ResponseStatus responseStatus;
	
}
