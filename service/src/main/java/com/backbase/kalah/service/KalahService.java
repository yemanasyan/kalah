package com.backbase.kalah.service;

import com.backbase.kalah.entity.Kalah;

/**
 * Kalah service.
 *
 * @author Yengibar Manasyan
 */
public interface KalahService {

	/**
	 * Update existing Kalah.
	 *
	 * @param kalah kalah to update
	 * @return saved kalah
	 */
	Kalah update(Kalah kalah);

	/**
	 * Create new Kalah.
	 *
	 * @return created kalah
	 */
	Kalah create();
}
