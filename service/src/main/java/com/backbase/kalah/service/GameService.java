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
	 *
	 * @param player player
	 * @return the game
	 */
	Game enterToGame(Player player);

	/**
	 * Find game by UUID.
	 *
	 * @param id game UUID
	 * @return found game
	 */
	Game findByUUID(UUID id);
}
