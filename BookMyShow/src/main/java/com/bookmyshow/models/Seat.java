package com.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel {
	private int rowNumber;
	private int colNumber;
	private String seatNumber;
	@Enumerated(EnumType.ORDINAL)
	private SeatType seatType;
}
