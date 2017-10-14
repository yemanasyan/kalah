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
	 * Find by id.
	 *
	 * @param id Player id
	 * @return found Player
	 */
	Player findById(UUID id);
}
