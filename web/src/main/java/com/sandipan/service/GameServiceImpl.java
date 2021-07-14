package com.sandipan.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandipan.Game;
import com.sandipan.MessageGenerator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

	// == fields ==
	private final Game game;
	private final MessageGenerator messageGenarator;

	// == constructors ==
	@Autowired
	public GameServiceImpl(Game game, MessageGenerator messageGenarator) {
		this.game = game;
		this.messageGenarator = messageGenarator;
	}
	
	// == init method ==
	@PostConstruct
	public void init() {
		log.info("number = {}", game.getNumber());
		log.info("mainMessage = {}", messageGenarator.getMainMessage());
	}
	
	// == public methods ==
	@Override
	public boolean isGameOver() {
		return game.isGameWon() || game.isGameLost();
	}

	@Override
	public String getMainMessage() {
		return messageGenarator.getMainMessage();
	}

	@Override
	public String getResultMessage() {
		return messageGenarator.getResultMessage();
	}

	@Override
	public void checkGuess(int guess) {
		game.setGuess(guess);
		game.check();
	}

	@Override
	public void reset() {
		game.reset();
	}

}
