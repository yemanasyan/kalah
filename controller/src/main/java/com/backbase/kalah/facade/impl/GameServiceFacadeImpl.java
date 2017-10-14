package com.backbase.kalah.facade.impl;

import com.backbase.kalah.bean.GameBean;
import com.backbase.kalah.entity.Game;
import com.backbase.kalah.entity.Player;
import com.backbase.kalah.facade.GameServiceFacade;
import com.backbase.kalah.service.GameService;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Facade class to convert beans to entities.
 *
 * @author Yengibar Manasyan
 */
@Service
public class GameServiceFacadeImpl implements GameServiceFacade {

	private final GameService gameService;

	/**
	 * Init based on provided beans.
	 *
	 * @param gameService game service
	 */
	public GameServiceFacadeImpl(GameService gameService) {
		this.gameService = gameService;
	}

	/**
	 * Convert game entity to bean.
	 *
	 * @param game     game entity
	 * @param playerId player UUID
	 * @return created bean
	 */
	private static GameBean convertToGameBean(Game game, UUID playerId) {
		final GameBean gameBean = new GameBean();
		gameBean.setPlayerId(playerId);
		gameBean.setFinished(game.getFinished());
		final Player player1 = game.getPlayer1();
		final Player player2 = game.getPlayer2();
		gameBean.setStartGame(player1 != null && player2 != null);

		final Player player;
		final Player opponent;
		if (player1 != null && playerId.equals(player1.getUuid())) {
			player = player1;
			opponent = player2;
		} else {
			player = player2;
			opponent = player1;
		}

		// if player is not null kalah also shouldn't be null, null check is written for safety
		if (player != null && player.getKalah() != null) {
			gameBean.setPits(player.getKalah().getPits());
			gameBean.setHome(player.getKalah().getHome());
		}

		if (opponent != null && opponent.getKalah() != null) {
			gameBean.setOpponentPits(opponent.getKalah().getPits());
			gameBean.setOpponentHome(opponent.getKalah().getHome());
		}

		return gameBean;
	}

	@Override
	public GameBean enterToGame() {
		final Game game = gameService.enterToGame();
		final UUID playerId = game.getPlayer1() != null ? game.getPlayer1().getUuid() : game.getPlayer2().getUuid();
		return convertToGameBean(game, playerId);
	}

	@Override
	public GameBean getGameByPlayerId(UUID playerId) {
		final Game game = gameService.findByPlayerUuid(playerId);
		return convertToGameBean(game, playerId);
	}

	@Override
	public GameBean play(UUID playerId, Integer position) {
		final Game game = gameService.play(playerId, position);
		return convertToGameBean(game, playerId);
	}
}
