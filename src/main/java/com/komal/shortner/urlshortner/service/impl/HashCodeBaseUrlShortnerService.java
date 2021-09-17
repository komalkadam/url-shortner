/**
 * 
 */
package com.komal.shortner.urlshortner.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;

import com.komal.shortner.urlshortner.exception.InvalidUrlException;
import com.komal.shortner.urlshortner.service.UrlShortnerService;
import com.komal.shortner.urlshortner.validator.UrlValidator;

/**
 * @author kkadam
 *
 */
@Service
public class HashCodeBaseUrlShortnerService implements UrlShortnerService {

	@Override
	public String shortenedUrl(String url) throws InvalidUrlException, UnsupportedEncodingException {
		UrlValidator.validate(url);
		String encodedURL = URLEncoder.encode(url, "UTF-8");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String originalUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

}
