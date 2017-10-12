package com.backbase.kalah.service;

import com.backbase.kalah.exception.EntityNotFoundException;
import com.backbase.kalah.exception.NotPlayerTurnException;

import java.util.UUID;

/**
 * Kalah service.
 *
 * @author Yengibar Manasyan
 */
public interface KalahService {

	/**
	 * Play the game.
	 *
	 * @param position   position from which stones where taken.
	 * @param playerUuid player uuid
	 * @throws EntityNotFoundException if not found player
	 * @throws NotPlayerTurnException  if it's not players turn
	 */
	void play(Integer position, UUID playerUuid) throws EntityNotFoundException, NotPlayerTurnException;
}
