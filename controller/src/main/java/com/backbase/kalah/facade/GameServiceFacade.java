package com.backbase.kalah.facade;

import com.backbase.kalah.bean.GameBean;
import com.backbase.kalah.exception.EmptyPitException;
import com.backbase.kalah.exception.EntityNotFoundException;
import com.backbase.kalah.exception.NotPlayerTurnException;

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
	 * @throws EntityNotFoundException if not found player
	 * @throws NotPlayerTurnException  if it's not players turn
	 * @throws EmptyPitException       if pit with provided position is empty
	 */
	GameBean play(UUID playerId, Integer position) throws EntityNotFoundException, EmptyPitException, NotPlayerTurnException;
}
