package com.masiv.esleida.cardenas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masiv.esleida.cardenas.models.Bet;
import com.masiv.esleida.cardenas.models.Roulette;
import com.masiv.esleida.cardenas.services.RouletteService;

@RestController
@RequestMapping("/api/roulettes")
public class RouletteController {

	@Autowired
	private RouletteService rouletteService;
	
	@PostMapping
	public Long createRoulette() {
		return rouletteService.createRoulette();
	}
	
	@GetMapping
	public List<Roulette> listRoulettes() {
		return rouletteService.listRoulettes();
	}
	
	@PutMapping
	public String openRoulette(@RequestBody Roulette roulette) {
		return rouletteService.openRoulette(roulette.getId());
	}
	
	@PostMapping("/bet")
	public ResponseEntity<?> toBet(@RequestBody Bet bet, @RequestHeader Long idUsuario) {
		try {
			return ResponseEntity.ok(rouletteService.toBet(bet));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping("/bet/closed")
	public ResponseEntity<?> closedBet(@RequestBody Roulette roulette) {
		try {
			return ResponseEntity.ok(rouletteService.closedBets(roulette.getId()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}
