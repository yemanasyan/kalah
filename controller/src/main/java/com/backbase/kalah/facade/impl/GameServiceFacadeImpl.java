package com.backbase.kalah.facade.impl;

import com.backbase.kalah.bean.GameBean;
import com.backbase.kalah.entity.Game;
import com.backbase.kalah.entity.Player;
import com.backbase.kalah.facade.GameServiceFacade;
import com.backbase.kalah.service.GameService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Facade class to convert beans to entities.
 *
 * @author Yengibar Manasyan
 */
@Service
public class GameServiceFacadeImpl implements GameServiceFacade {

	private final MapperFacade mapperFacade;

	private final GameService gameService;

	/**
	 * Init based on provided beans.
	 *
	 * @param mapperFacade mapper facade
	 * @param gameService  game service
	 */
	public GameServiceFacadeImpl(MapperFacade mapperFacade, GameService gameService) {
		this.mapperFacade = mapperFacade;
		this.gameService = gameService;
	}

	private static GameBean convertToPlayerBean(Game game) {
		final GameBean gameBean = new GameBean();
		final Player player1 = game.getPlayer1();
		final Player player2 = game.getPlayer2();
		gameBean.setPlayerId(player2 != null ? player2.getUuid() : player1.getUuid());
		gameBean.setFinished(false);
		gameBean.setStartGame(player1 != null && player2 != null);

		return gameBean;
	}

	@Override
	public GameBean enterToGame() {
		final Game game = gameService.enterToGame();
		return convertToPlayerBean(game);
	}

	@Override
	public GameBean getGameByPlayerId(UUID playerId) {
		final Game game = gameService.findByPlayerUuid(playerId);
		final GameBean gameBean = new GameBean();
		gameBean.setPlayerId(playerId);
		gameBean.setFinished(game.getFinished());
		final Player player1 = game.getPlayer1();
		final Player player2 = game.getPlayer2();
		gameBean.setStartGame(player1 != null && player2 != null);

		final Player player;
		final Player opponent;
		if (player1 != null && player1.getUuid().equals(playerId)) {
			player = player1;
			opponent = player2;
		} else {
			player = player2;
			opponent = player1;
		}

		gameBean.setPits(player.getKalah().getPits());
		gameBean.setHome(player.getKalah().getHome());
		gameBean.setOpponentPits(opponent.getKalah().getPits());
		gameBean.setOpponentHome(opponent.getKalah().getHome());

		return gameBean;
	}
}
