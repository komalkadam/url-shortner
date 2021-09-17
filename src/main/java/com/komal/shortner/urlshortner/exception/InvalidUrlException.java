/**
 * 
 */
package com.komal.shortner.urlshortner.exception;

import org.springframework.http.HttpStatus;

import com.komal.shortner.urlshortner.exception.dto.UrlShortnerRestError;

/**
 * @author kkadam
 *
 */
public class InvalidUrlException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5831291088863860554L;
	private HttpStatus status;
	private String message;
	private String debugMessage;
	
	public InvalidUrlException(HttpStatus status) {
		this.status = status;
	}
	
	public InvalidUrlException(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public InvalidUrlException(UrlShortnerRestError error) {
		this.status = error.getStatus();
		this.message = error.getMessage();
		this.debugMessage  = error.getDebugMessage();
		this.timestamp = error.getTimestamp();
	}
	
	
	public InvalidUrlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus status) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.message = message;
		this.status = status;
	}
	public InvalidUrlException(String message, Throwable cause, HttpStatus status) {
		super(message, cause);
		this.message = message;
		this.status = status;
	}
	public InvalidUrlException(String message, HttpStatus status) {
		super(message);
		this.message = message;
		this.status = status;
	}
	public InvalidUrlException(Throwable cause, HttpStatus status) {
		super(cause);
		this.message = cause.getMessage();
		this.status = status;
	}
	
	public InvalidUrlException(String message) {
		super(message);
	}

	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDebugMessage() {
		return debugMessage;
	}
	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private long timestamp;
}
