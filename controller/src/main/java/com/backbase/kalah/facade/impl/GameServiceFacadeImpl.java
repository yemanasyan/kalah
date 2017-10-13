package com.backbase.kalah.facade.impl;

import com.backbase.kalah.bean.GameBean;
import com.backbase.kalah.entity.Game;
import com.backbase.kalah.facade.GameServiceFacade;
import com.backbase.kalah.service.GameService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

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

	@Override
	public GameBean enterToGame() {
		final Game game = gameService.enterToGame();
		final GameBean gameBean = new GameBean();
		gameBean.setUuid(game.getUuid());
		gameBean.setPlayerUuid(game.getPlayer2() != null ? game.getPlayer2().getUuid() : game.getPlayer1().getUuid());
		gameBean.setFinished(false);
		gameBean.setStartGame(game.getPlayer1() != null && game.getPlayer2() != null);

		return gameBean;
	}
}
