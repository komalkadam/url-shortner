/**
 * Common Interface for URL shortner
 * We can have different implememntations for it depending on the choces
 * 
 */
package com.komal.shortner.urlshortner.service;

import java.io.UnsupportedEncodingException;

import com.komal.shortner.urlshortner.exception.InvalidUrlException;

/**
 * @author kkadam
 *
 */
public interface UrlShortnerService {
	String shortenedUrl(String url) throws InvalidUrlException, UnsupportedEncodingException;
	
	String originalUrl(String url) throws InvalidUrlException;
	
}
