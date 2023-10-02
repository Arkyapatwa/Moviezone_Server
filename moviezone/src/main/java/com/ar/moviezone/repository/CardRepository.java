package com.ar.moviezone.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ar.moviezone.entity.Card;

public interface CardRepository extends CrudRepository<Card, Integer> {

	List<Card> findByUserEmailId(String userEmailId);
	List<Card> findByUserEmailIdAndCardType(String userEmailId, String cardType);
	Card findByUserEmailIdAndCardNumber(String userEmailId, String cardNumber);
}
