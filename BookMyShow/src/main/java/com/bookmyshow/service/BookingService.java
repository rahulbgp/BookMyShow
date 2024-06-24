package com.bookmyshow.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.bookmyshow.exception.ShowNotFoundException;
import com.bookmyshow.exception.UserNotFoundException;
import com.bookmyshow.models.Booking;
import com.bookmyshow.models.BookingStatus;
import com.bookmyshow.models.Show;
import com.bookmyshow.models.ShowSeat;
import com.bookmyshow.models.ShowSeatStatus;
import com.bookmyshow.models.User;
import com.bookmyshow.repository.BookingRepository;
import com.bookmyshow.repository.ShowRepository;
import com.bookmyshow.repository.ShowSeatRepository;
import com.bookmyshow.repository.UserRepository;

public class BookingService {
	private UserRepository userRepository;
	private ShowRepository showRepository;
	private ShowSeatRepository showSeatRepository;
	private PriceCalculator priceCalculator;
	private BookingRepository bookingRepository;

	public BookingService(UserRepository userRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, PriceCalculator priceCalculator, BookingRepository bookingRepository) {
		this.userRepository = userRepository;
		this.showRepository = showRepository;
		this.showRepository = showRepository;
		this.priceCalculator = priceCalculator;
		this.bookingRepository = bookingRepository;
	}
	
    @Transactional(isolation = Isolation.SERIALIZABLE)
	public Booking createBooking(Long userId, List<Long> showSeatIds, Long showId) throws UserNotFoundException, ShowNotFoundException {
		/*
		 * 1. Get user with given user id
		 * 2. Get show with given show id
		 * 3. Get List<ShowSeat> with the given id
		 * 4.Check if all seat are available or not
		 * 5.If not throw exception
		 * 6.If yes, change the status of all seat to BLOCKED
		 * 7. save the change in db as well
		 * 8. create booking with pending status [save booking obj to DB.]
		 * 9. return the booking object
		 */
		//1. Get user with given user id
		Optional<User> optionalUser = userRepository.findById(userId);
		
		if(optionalUser.isEmpty()) {
			throw new UserNotFoundException("User with id: " + userId +" not found.");
		}
		
		User user = optionalUser.get();
		
		//2. Get show with given show id
		Optional<Show> optionalShow = showRepository.findById(showId);
		
		if(optionalShow.isEmpty()) {
			throw new ShowNotFoundException("Show with id: " + showId +" not found.");
		}
		
		Show show = optionalShow.get();
		//3. Get List<ShowSeat> with the given id
		List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
		
		//4.Check if all seat are available or not
		for(ShowSeat showSeat : showSeats) {
			if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
				//5.If not throw exception
				throw new RuntimeException("Show Seat with id: " + showSeat.getId() + " is not available.");
				
			}
		}
		//6.If yes, change the status of all seat to BLOCKED
		for(ShowSeat showSeat : showSeats) {
			showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
			//7. save the change in db as well
			showSeatRepository.save(showSeat);
		}
		
		// 8. create booking with pending status
		Booking booking = new Booking();
		booking.setBookingStatus(BookingStatus.PENDING);
		booking.setCreatedAt(new Date());
		booking.setUser(user);
		booking.setShow(show);
		booking.setPayments(new ArrayList<>());
		booking.setShowSeats(showSeats);
		booking.setAmount(priceCalculator.priceCalculator(show, showSeats));
		
		//9. return the booking object
		return bookingRepository.save(booking);
		
	}
	
	
	

}
