package com.masiv.esleida.cardenas.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "BetResults")
@Entity
public class BetResult {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long idRoulette;
	
	private Long idBet;
	
	private Double amountEarned;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdRoulette() {
		return idRoulette;
	}

	public void setIdRoulette(Long idRoulette) {
		this.idRoulette = idRoulette;
	}

	public Long getIdBet() {
		return idBet;
	}

	public void setIdBet(Long idBet) {
		this.idBet = idBet;
	}

	public Double getAmountEarned() {
		return amountEarned;
	}

	public void setAmountEarned(Double amountEarned) {
		this.amountEarned = amountEarned;
	}
}
