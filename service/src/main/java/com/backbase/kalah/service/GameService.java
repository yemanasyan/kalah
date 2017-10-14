package com.backbase.kalah.service;

import com.backbase.kalah.entity.Game;
import com.backbase.kalah.entity.Player;

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
	 */
	Game play(UUID playerUuid, Integer position);
}
