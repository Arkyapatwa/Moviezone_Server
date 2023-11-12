package com.ar.moviezone.repository;

import org.springframework.data.repository.CrudRepository;

import com.ar.moviezone.entity.Seat;

public interface SeatRepository extends CrudRepository<Seat, Integer> {

}
