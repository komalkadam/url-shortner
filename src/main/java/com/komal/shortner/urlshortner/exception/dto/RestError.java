package com.komal.shortner.urlshortner.exception.dto;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.springframework.http.HttpStatus;

import com.komal.shortner.urlshortner.exception.RestException;


public class RestError {

	private HttpStatus status;
	
	private String message;
	private String debugMessage;
	private long timestamp;

	private RestError() {
		this.timestamp = System.currentTimeMillis();
	}

	public RestError(HttpStatus status) {
		this();
		this.status = status;
	}
	
	
	public RestError(RestException exc) {
		this();
		this.status = exc.getStatus();
		this.message = exc.getMessage();
		this.debugMessage = exc.getDebugMessage();
		this.timestamp = exc.getTimestamp();
	}

	public RestError(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();
	}

	public RestError(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
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

	public void setStackTraceAsDebugMessage(Throwable aThrowable) {
		Writer result = null;
		PrintWriter printWriter = null;
		try {
			result = new StringWriter();
			printWriter = new PrintWriter(result);
			aThrowable.printStackTrace(printWriter);
			this.debugMessage = result.toString();
		} catch (Exception ex) {
			this.debugMessage = aThrowable.getLocalizedMessage();
		} finally {
			if (printWriter != null) {
				printWriter.close();
			}
			if (result != null) {
				try {
					result.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
