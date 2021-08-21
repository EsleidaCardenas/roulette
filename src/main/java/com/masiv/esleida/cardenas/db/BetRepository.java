package com.masiv.esleida.cardenas.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.masiv.esleida.cardenas.models.Bet;

public interface BetRepository extends CrudRepository<Bet, Long>{
	
	public List<Bet> findByIdRoulette(Long id);
}
