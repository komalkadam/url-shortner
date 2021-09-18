/**
 * Common Interface for URL shortner
 * We can have different implememntations for it depending on the choces
 * 
 */
package com.komal.shortner.urlshortner.service;

import com.komal.shortner.urlshortner.exception.InvalidUrlException;
import com.komal.shortner.urlshortner.model.OriginalUrl;

/**
 * @author kkadam
 *
 */
public interface UrlShortnerService {
	String shortenedUrl(OriginalUrl url) throws InvalidUrlException;
	
	String originalUrl(String url) throws InvalidUrlException;
	
}
