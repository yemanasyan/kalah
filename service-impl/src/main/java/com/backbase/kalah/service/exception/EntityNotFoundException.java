package com.backbase.kalah.service.exception;

/**
 * Throw this exception if required entity not found.
 *
 * @author Yengibar Manasyan
 */
public class EntityNotFoundException extends KalahGameException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message) {
		super(message);
	}

}
