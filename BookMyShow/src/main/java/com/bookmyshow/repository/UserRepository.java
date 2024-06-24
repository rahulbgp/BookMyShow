package com.bookmyshow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmyshow.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	 Optional<User> findById(Long userId);
	 Optional<User> findByEmail(String email);

}
