package com.bookmyshow.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseModel {
	private String name;
	private String email;
	private String password;
	@OneToMany
	private List<Booking> bookings; 

}
