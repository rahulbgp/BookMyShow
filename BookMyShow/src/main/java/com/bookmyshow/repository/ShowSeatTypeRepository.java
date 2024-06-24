package com.bookmyshow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmyshow.models.Show;
import com.bookmyshow.models.ShowSeatType;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
	List<ShowSeatType> findAllByShow(Show show);

}
