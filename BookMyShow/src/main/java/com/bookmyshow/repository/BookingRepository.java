package com.bookmyshow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmyshow.models.Booking;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	Booking save(Booking booking);

}
