package com.sandipan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sandipan.service.GameService;
import com.sandipan.utill.AttributesName;
import com.sandipan.utill.GameMappings;
import com.sandipan.utill.ViewNames;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class GameController {

	// == fields ==
	private final GameService gameService;

	@Autowired
	public GameController(GameService gameService) {
		this.gameService = gameService;
	}

	// == request methods ==
	@GetMapping(GameMappings.PLAY)
	public String play(Model model) {
		model.addAttribute(AttributesName.MAIN_MESSAGE, gameService.getMainMessage());
		model.addAttribute(AttributesName.RESULT_MESSAGE, gameService.getResultMessage());
		log.info("model= {}", model);
		if (gameService.isGameOver()) {
			return ViewNames.GAME_OVER;
		}

		return ViewNames.PLAY;
	}

	@PostMapping(GameMappings.PLAY)
	public String processMessage(@RequestParam int guess) {
		log.info("guess = {}", guess);
		gameService.checkGuess(guess);
		return GameMappings.REDIRECT_PLAY;
	}
	
	@GetMapping(GameMappings.RESTART)
	public String restart() {
		gameService.reset();
		return GameMappings.REDIRECT_PLAY;
	}
	
}
