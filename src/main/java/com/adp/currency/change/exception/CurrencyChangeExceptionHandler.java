package com.adp.currency.change.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.adp.currency.change.to.ExceptionResponse;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CurrencyChangeExceptionHandler {

	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ExceptionResponse handleResourceNotFound(final HttpClientErrorException exception,
			final HttpServletRequest request) {

		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setRequestedURI(request.getRequestURI());

		return error;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleException(final Exception exception,
			final HttpServletRequest request) {

		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setRequestedURI(request.getRequestURI());

		return error;
	}
}
