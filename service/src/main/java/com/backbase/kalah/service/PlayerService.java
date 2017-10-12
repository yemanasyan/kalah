package com.backbase.kalah.service;

import com.backbase.kalah.entity.Player;
import com.backbase.kalah.exception.EmptyPitException;
import com.backbase.kalah.exception.EntityNotFoundException;
import com.backbase.kalah.exception.NotPlayerTurnException;

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
}
