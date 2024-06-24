package com.bookmyshow.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequestDto {
	private Long userId;
	private Long showId;
	private List<Long>  showSeatId;

}
