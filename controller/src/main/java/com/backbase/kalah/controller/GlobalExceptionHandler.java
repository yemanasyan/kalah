package com.backbase.kalah.controller;

import com.backbase.kalah.service.exception.EmptyPitException;
import com.backbase.kalah.service.exception.EntityNotFoundException;
import com.backbase.kalah.service.exception.NotPlayerTurnException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Handle all exception and change status codes.
 *
 * @author Yengibar Manasyan
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * Default constructor.
	 */
	public GlobalExceptionHandler() {
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EntityNotFoundException.class)
	public String handleEntityNotFoundException(HttpServletRequest request, EntityNotFoundException ex) {
		final String exceptionMsg = String.format("Error while processing request: %s?%s", request.getRequestURL(), request.getQueryString());
		LOGGER.error(exceptionMsg, ex);

		return ex.getMessage();
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({EmptyPitException.class, NotPlayerTurnException.class, IllegalArgumentException.class})
	public String handleEmptyPitException(HttpServletRequest request, Exception ex) {
		final String exceptionMsg = String.format("Error while processing request: %s?%s", request.getRequestURL(), request.getQueryString());
		LOGGER.error(exceptionMsg, ex);

		return ex.getMessage();
	}
}
