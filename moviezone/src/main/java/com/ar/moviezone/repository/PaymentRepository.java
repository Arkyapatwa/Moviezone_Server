package com.ar.moviezone.repository;

import org.springframework.data.repository.CrudRepository;

import com.ar.moviezone.entity.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Integer>{
	
}
