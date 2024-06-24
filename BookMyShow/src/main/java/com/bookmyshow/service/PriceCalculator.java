package com.bookmyshow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookmyshow.models.Show;
import com.bookmyshow.models.ShowSeat;
import com.bookmyshow.models.ShowSeatType;
import com.bookmyshow.repository.ShowSeatTypeRepository;

@Service
public class PriceCalculator {
	int amount = 0;
	private ShowSeatTypeRepository showSeatTypeRepository;
	
	public PriceCalculator(ShowSeatTypeRepository showSeatTypeRepository) {
		this.showSeatTypeRepository = showSeatTypeRepository;
	}
	public  int priceCalculator(Show show, List<ShowSeat> showSeats) {
		List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);
		for(ShowSeat showSeat : showSeats) {
			for(ShowSeatType showSeatType : showSeatTypes) {
				if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
					amount += showSeatType.getPrice();
					break;
				}
			}
		}
		return amount;
	}
	

}
