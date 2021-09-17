package com.komal.shortner.urlshortner.exception.handler;

import java.io.UnsupportedEncodingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.komal.shortner.urlshortner.exception.InvalidUrlException;
import com.komal.shortner.urlshortner.exception.RestException;
import com.komal.shortner.urlshortner.exception.dto.RestError;
import com.komal.shortner.urlshortner.exception.dto.UrlShortnerRestError;

@ControllerAdvice
public class UrlShortnerGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = InvalidUrlException.class)
	public ResponseEntity<UrlShortnerRestError> handleBaseException(InvalidUrlException e) {
		UrlShortnerRestError error = new UrlShortnerRestError(HttpStatus.BAD_REQUEST);
		setRestErrorProperties(e, error);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(value = UnsupportedEncodingException.class)
	public ResponseEntity<UrlShortnerRestError> handleBaseException(UnsupportedEncodingException e) {
		UrlShortnerRestError error = new UrlShortnerRestError(HttpStatus.INTERNAL_SERVER_ERROR);
		setRestErrorProperties(e, error);
		error.setMessage("Unsupported encoding exception");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}


	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<UrlShortnerRestError> handleException(Exception e) {
		UrlShortnerRestError error = new UrlShortnerRestError(HttpStatus.INTERNAL_SERVER_ERROR);
		setRestErrorProperties(e, error);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}

	
	
	@ExceptionHandler(value = RestException.class)
	public ResponseEntity<RestError> handleBaseException(RestException e) {
		RestError error = new RestError(e.getStatus());
		setRestErrorProperties(e, error);
		return ResponseEntity.status(e.getStatus()).body(error);
	}
	
	
	private void setRestErrorProperties(Exception e, UrlShortnerRestError error) {
		error.setMessage(e.getMessage());
		error.setDebugMessage(error.getDebugMessage());
		error.setTimestamp(System.currentTimeMillis());
	}

	private void setRestErrorProperties(Exception e, RestError error) {
		error.setMessage(e.getMessage());
		error.setDebugMessage(error.getDebugMessage());
		error.setTimestamp(System.currentTimeMillis());
	}
}
