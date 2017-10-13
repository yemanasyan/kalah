package com.backbase.kalah.service;

import com.backbase.kalah.entity.Kalah;

import java.util.UUID;

/**
 * Kalah service.
 *
 * @author Yengibar Manasyan
 */
public interface KalahService {

	/**
	 * Save Kalah.
	 *
	 * @param kalah kalah to save
	 * @return saved kalah
	 */
	Kalah save(Kalah kalah);
}
