package com.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDto {
	private Long userId;
	private ResponseStatus responseStatus;

}
