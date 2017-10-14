package com.backbase.kalah.facade;

import com.backbase.kalah.bean.GameBean;

import java.util.UUID;

/**
 * Facade interface to convert beans to entities.
 *
 * @author Yengibar Manasyan
 */
public interface GameServiceFacade {

	/**
	 * Enter or create a new game.
	 *
	 * @return created or joined game
	 */
	GameBean enterToGame();

	/**
	 * Get game by uuid.
	 *
	 * @param gameId game uuid
	 * @return found game
	 */
	GameBean getGameByPlayerId(UUID gameId);

	/**
	 * Play by position and return game bean.
	 *
	 * @param playerId player UUID
	 * @param position kalah position
	 * @return updated game bean
	 */
	GameBean play(UUID playerId, Integer position);
}
