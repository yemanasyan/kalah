package com.backbase.kalah.exception;

/**
 * Base exception.
 *
 * @author Yengibar Manasyan
 */
public class KalahGameException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Init based on provided message.
	 *
	 * @param message message of exception
	 */
	public KalahGameException(String message) {
		super(message);
	}

	/**
	 * Init based on provided {@code message} and {@code cause}.
	 *
	 * @param message message of exception
	 * @param cause   reason of this exception
	 */
	public KalahGameException(String message, Exception cause) {
		super(message, cause);
	}
}
