package com.masiv.esleida.cardenas.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masiv.esleida.cardenas.db.BetRepository;
import com.masiv.esleida.cardenas.db.RouletteRepository;
import com.masiv.esleida.cardenas.models.Bet;
import com.masiv.esleida.cardenas.models.BetResult;
import com.masiv.esleida.cardenas.models.Roulette;
import com.masiv.esleida.cardenas.utils.Color;
import com.masiv.esleida.cardenas.utils.Status;
import com.masiv.esleida.cardenas.utils.StatusRoulette;

@Service
public class RouletteService {
	
	public final String NOT_EXIST_ROULETTE = "No existe la ruleta";
	public final int MAX_AMMOUNT = 10000;
	public final String ERROR_MAX_AMMOUNT = "El monto maximo de apuesta es de 10000 dolares";
	public final String ERROR_ROULETTE_CLOSED = "Solo se puede apostar a ruletas abiertas";
	public final String ERROR_NUMBER_BET = "Solo se puede apostar con numeros del 0 al 36";
	public final int MIN_NUMBER_BET = 0;
	public final int MAX_NUMBER_BET = 36;

	
	@Autowired
	private RouletteRepository rouletteRepository;
	
	@Autowired
	private BetRepository betRepository;

	public Long createRoulette() {
		Roulette roulette = new Roulette();
		roulette.setStatus(StatusRoulette.CLOSED.getName());
		rouletteRepository.save(roulette);
		return roulette.getId();
	}
	
	public List<Roulette> listRoulettes() {
		return StreamSupport.stream(rouletteRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}
	
	public String openRoulette(Long id) {
		try {
			Optional<Roulette> roulette = rouletteRepository.findById(id);
			if(!roulette.isPresent()) {
				return NOT_EXIST_ROULETTE;
			}
			Roulette rouletteOpen = roulette.get();
			rouletteOpen.setStatus(StatusRoulette.OPEN.getName());
			rouletteRepository.save(rouletteOpen);
			return Status.SUCCESS.getName();
		} catch (Exception e) {
			return Status.DENIED.getName();
		}
	}
	
	public Long toBet(Bet bet) throws Exception{
		if(bet.getAmmount() > MAX_AMMOUNT) {
			throw new Exception(ERROR_MAX_AMMOUNT);
		}
		
		if(bet.getNumber() < MIN_NUMBER_BET || bet.getNumber() > MAX_NUMBER_BET) {
			throw new Exception(ERROR_NUMBER_BET);
		}
		
		Optional<Roulette> roulette = rouletteRepository.findById(bet.getIdRoulette());
		if(!roulette.isPresent()) {
			throw new Exception(NOT_EXIST_ROULETTE);
		}
		
		if(roulette.get().getStatus().equals(StatusRoulette.CLOSED.getName())) {
			throw new Exception(ERROR_ROULETTE_CLOSED);
		}
		
		betRepository.save(bet);
		return bet.getId();
	}
	
	public List<BetResult> closedBets(Long idRoulette) {
		List<Bet> beds = betRepository.findByIdRoulette(idRoulette);
		int numberWin = (int) (Math.random() * (36 - 0)) + 0;
		String colorWin = numberWin%2==0  ? Color.RED.getName() : Color.BLACK.getName();		
		return beds.stream().map(bet -> {
			BetResult betResult = new BetResult();
			betResult.setIdRoulette(idRoulette);
			betResult.setIdBet(bet.getId());
			if(bet.getNumber() == numberWin) {
				betResult.setAmountEarned(bet.getAmmount() * 5);
			} else if(colorWin.equals(bet.getColor())) {
				betResult.setAmountEarned(bet.getAmmount() * 1.8);
			} else {
				betResult.setAmountEarned(0.0);
			}
			
			return betResult;
		}).collect(Collectors.toList());
		
	}
}
