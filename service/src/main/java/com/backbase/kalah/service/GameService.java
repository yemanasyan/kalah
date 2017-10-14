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
	 * Find game by id.
	 *
	 * @param id game id
	 * @return found game
	 */
	Game findById(UUID id);

	/**
	 * Find by player id.
	 *
	 * @param playerId player id
	 * @return found game
	 */
	Game findByPlayerId(UUID playerId);

	/**
	 * Find opponent by player id.
	 *
	 * @param playerId player id
	 * @return opponent or null if there is not
	 */
	Player findGameOpponentByPlayerId(UUID playerId);

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
	 * @param playerId player id
	 * @param position   position from which stones where taken.
	 * @return changed game
	 */
	Game play(UUID playerId, Integer position);
}
