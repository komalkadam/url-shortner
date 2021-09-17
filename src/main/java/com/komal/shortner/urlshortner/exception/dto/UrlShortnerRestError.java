package com.komal.shortner.urlshortner.exception.dto;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.springframework.http.HttpStatus;

import com.komal.shortner.urlshortner.exception.InvalidUrlException;

/**
 * @author kkadam
 *
 */
public class UrlShortnerRestError {

	private HttpStatus status;
	
	private String message;
	private String debugMessage;
	private long timestamp;

	private UrlShortnerRestError() {
		this.timestamp = System.currentTimeMillis();
	}

	public UrlShortnerRestError(HttpStatus status) {
		this();
		this.status = status;
	}
	
	
	public UrlShortnerRestError(InvalidUrlException exc) {
		this();
		this.status = exc.getStatus();
		this.message = exc.getMessage();
		this.debugMessage = exc.getDebugMessage();
		this.timestamp = exc.getTimestamp();
	}

	public UrlShortnerRestError(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();
	}

	public UrlShortnerRestError(HttpStatus status, String message, Throwable ex) {
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
