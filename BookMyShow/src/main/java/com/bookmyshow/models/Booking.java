package com.bookmyshow.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {
	private String bookingNumber;
	@ManyToOne
	private User user;
	@ManyToOne
	private Show show;
	@ManyToMany
	private List<ShowSeat> showSeats;
	private int amount;
	@OneToMany
	private List<Payment> payments;
	@Enumerated(EnumType.ORDINAL)
	private BookingStatus bookingStatus;

}
