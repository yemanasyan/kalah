package com.backbase.kalah.controller;

import com.backbase.kalah.bean.GameBean;
import com.backbase.kalah.facade.GameServiceFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api(description = "Game endpoints")
@RestController
@RequestMapping("/games")
public class GameController {

	private static final String PLAYER_ID = "player-id";

	private static final String POSITION = "position";

	private final GameServiceFacade gameServiceFacade;

	public GameController(GameServiceFacade gameServiceFacade) {
		this.gameServiceFacade = gameServiceFacade;
	}

	// TODO add exception handling and logging
	@ApiOperation(value = "Enter to new game.", response = GameBean.class)
	@PostMapping
	public GameBean enterToGame() {
		return gameServiceFacade.enterToGame();
	}

	@ApiOperation(value = "Get game information by player id.", response = GameBean.class)
	@GetMapping
	public GameBean getByPlayerId(@ApiParam(required = true) @RequestParam(PLAYER_ID) UUID playerId) {
		return gameServiceFacade.getGameByPlayerId(playerId);
	}

	@ApiOperation(value = "Play Kalah game.", response = GameBean.class)
	@PostMapping("/play")
	public GameBean play(@ApiParam(required = true) @RequestParam(PLAYER_ID) UUID playerId,
	                     @ApiParam(required = true, allowableValues = "range[0,5]") @RequestParam(POSITION) Integer position) {
		return gameServiceFacade.play(playerId, position);
	}
}
