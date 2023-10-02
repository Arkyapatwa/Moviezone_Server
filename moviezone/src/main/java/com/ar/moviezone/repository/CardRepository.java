package com.ar.moviezone.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ar.moviezone.entity.Card;

public interface CardRepository extends CrudRepository<Card, Integer> {

	List<Card> findByCustomerEmailId(String userEmailId);
	List<Card> findByCustomerEmailIdAndCardType(String userEmailId, String cardType);
}
