package com.komal.shortner.urlshortner.exception;

import org.springframework.http.HttpStatus;

import com.komal.shortner.urlshortner.exception.dto.RestError;


/**
 * @author kkadam
 *
 */
public class RestException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8330647656386933610L;
	private HttpStatus status;
	private String message;
	private String debugMessage;
	private long timestamp;
	
	
	public RestException(HttpStatus status) {
		this.status = status;
	}
	
	public RestException(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public RestException(RestError error) {
		this.status = error.getStatus();
		this.message = error.getMessage();
		this.debugMessage  = error.getDebugMessage();
		this.timestamp = error.getTimestamp();
	}
	
	
	public RestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus status) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.message = message;
		this.status = status;
	}
	public RestException(String message, Throwable cause, HttpStatus status) {
		super(message, cause);
		this.message = message;
		this.status = status;
	}
	public RestException(String message, HttpStatus status) {
		super(message);
		this.message = message;
		this.status = status;
	}
	public RestException(Throwable cause, HttpStatus status) {
		super(cause);
		this.message = cause.getMessage();
		this.status = status;
	}

	
	public HttpStatus getStatus() {
		return status;
	}
	
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	@Override
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
	
}
