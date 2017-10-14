package com.backbase.kalah.service.exception;

/**
 * Throw this exception if player tried play from empty pit.
 *
 * @author Yengibar Manasyan
 */
public class EmptyPitException extends KalahGameException {

	public EmptyPitException(String message) {
		super(message);
	}
}