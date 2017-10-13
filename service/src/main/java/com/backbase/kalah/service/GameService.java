package com.backbase.kalah.service;

import com.backbase.kalah.entity.Game;
import com.backbase.kalah.entity.Player;
import com.backbase.kalah.exception.EmptyPitException;
import com.backbase.kalah.exception.EntityNotFoundException;
import com.backbase.kalah.exception.NotPlayerTurnException;

import java.util.UUID;

/**
 * Game service.
 *
 * @author Yengibar Manasyan
 */
public interface GameService {

	/**
	 * Enter to game.
	 * It should create a new Player also.
	 *
	 * @return the game
	 */
	Game enterToGame();

	/**
	 * Find game by UUID.
	 *
	 * @param id game UUID
	 * @return found game
	 */
	Game findByUUID(UUID id);

	/**
	 * Find by player id.
	 *
	 * @param playerId player id
	 * @return found game
	 */
	Game findByPlayerId(Long playerId);

	/**
	 * Find by player UUID.
	 *
	 * @param playerUuid player UUID
	 * @return found game
	 */
	Game findByPlayerUuid(UUID playerUuid);

	/**
	 * Find opponent by player id.
	 *
	 * @param playerId player id
	 * @return opponent or null if there is not
	 */
	Player findGameOpponentByPlayerId(Long playerId);

	/**
	 * Save game.
	 *
	 * @param game game to save
	 * @return saved game
	 */
	Game save(Game game);

	/**
	 * Play the game.
	 *
	 * @param playerUuid player uuid
	 * @param position   position from which stones where taken.
	 * @return changed game
	 * @throws EntityNotFoundException if not found player
	 * @throws NotPlayerTurnException  if it's not players turn
	 * @throws EmptyPitException       if pit with provided position is empty
	 */
	Game play(UUID playerUuid, Integer position) throws EntityNotFoundException, NotPlayerTurnException, EmptyPitException;
}
