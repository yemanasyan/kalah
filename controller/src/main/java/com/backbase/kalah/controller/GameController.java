package com.backbase.kalah.controller;

import com.backbase.kalah.bean.GameBean;
import com.backbase.kalah.exception.EmptyPitException;
import com.backbase.kalah.exception.EntityNotFoundException;
import com.backbase.kalah.exception.NotPlayerTurnException;
import com.backbase.kalah.facade.GameServiceFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * REST endpoint for player.
 *
 * @author Yengibar Manasyan
 */
@RestController
@RequestMapping("/games")
public class GameController {

	private final GameServiceFacade gameServiceFacade;

	public GameController(GameServiceFacade gameServiceFacade) {
		this.gameServiceFacade = gameServiceFacade;
	}

	@PostMapping
	public GameBean enterToGame() {
		return gameServiceFacade.enterToGame();
	}

	@GetMapping("/{playerId}")
	public GameBean getByPlayerId(@PathVariable UUID playerId) {
		return gameServiceFacade.getGameByPlayerId(playerId);
	}

	@PostMapping("/{playerId}")
	public GameBean play(@PathVariable UUID playerId, @RequestParam("position") Integer position) throws EntityNotFoundException, EmptyPitException, NotPlayerTurnException {
		return gameServiceFacade.play(playerId, position);
	}
}
