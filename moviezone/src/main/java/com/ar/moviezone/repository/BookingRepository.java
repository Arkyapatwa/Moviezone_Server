package com.ar.moviezone.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ar.moviezone.entity.Booking;

public interface BookingRepository extends CrudRepository<Booking, Integer> {

	List<Booking> findByUserEmailId(String emailId);
}
