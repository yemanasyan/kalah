package com.backbase.kalah.service.exception;

/**
 * Throw this exception if required entity not found.
 *
 * @author Yengibar Manasyan
 */
public class EntityNotFoundException extends KalahGameException {

	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException(String message, Exception cause) {
		super(message, cause);
	}
}
