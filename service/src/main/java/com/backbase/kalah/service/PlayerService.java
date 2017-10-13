package com.backbase.kalah.service;

import com.backbase.kalah.entity.Player;

import java.util.UUID;

/**
 * Player service.
 *
 * @author Yengibar Manasyan
 */
public interface PlayerService {

	/**
	 * Save Player.
	 *
	 * @param player player
	 * @return saved player
	 */
	Player save(Player player);

	/**
	 * Find by uuid.
	 *
	 * @param uuid uuid
	 * @return found Player
	 */
	Player findByUuid(UUID uuid);

	/**
	 * Find opponent by player id.
	 *
	 * @param id player id
	 * @return opponent or null if there is not
	 */
	Player findOpponent(Long id);
}
