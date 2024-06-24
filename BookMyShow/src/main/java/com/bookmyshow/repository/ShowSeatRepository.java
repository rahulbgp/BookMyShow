package com.bookmyshow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmyshow.models.ShowSeat;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

	//select * from show_seats where id IN (1, 2, 3, 4, 5)
	List<ShowSeat> findAllById(Iterable<Long> showSeatIds);
	
	ShowSeat save(ShowSeat showSeat);
	
	
		

}
