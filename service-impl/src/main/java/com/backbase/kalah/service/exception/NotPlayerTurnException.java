package com.backbase.kalah.service.exception;

/**
 * Throw it when player want to play not during his/her turn.
 *
 * @author Yengibar Manasyan
 */
public class NotPlayerTurnException extends KalahGameException {

	private static final long serialVersionUID = 1L;

	public NotPlayerTurnException(String message) {
		super(message);
	}
}
