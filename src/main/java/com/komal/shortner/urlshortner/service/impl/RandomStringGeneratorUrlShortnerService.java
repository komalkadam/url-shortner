/**
 * 
 */
package com.komal.shortner.urlshortner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.komal.shortner.urlshortner.dao.UrlShortnerDao;
import com.komal.shortner.urlshortner.exception.InvalidUrlException;
import com.komal.shortner.urlshortner.model.OriginalUrl;
import com.komal.shortner.urlshortner.service.RandomStringGenerator;
import com.komal.shortner.urlshortner.service.UrlShortnerService;
import com.komal.shortner.urlshortner.validator.UrlValidator;

/**
 * @author kkadam
 *
 */
@Service
public class RandomStringGeneratorUrlShortnerService implements UrlShortnerService {
	@Autowired
	UrlShortnerDao shortnerDao;
	
	@Value("${com.komal.urlshortner.short.url.start}")
	private String shortnerStart;
	
	@Value("${com.komal.urlshortner.short.url.length}")
	private int shortnedUrlLength;
	
	@Value("${com.komal.urlshortner.short.url.include.numbers}")
	private boolean includeNumbers;
	
	@Value("${com.komal.urlshortner.short.url.include.characters}")
	private boolean includeCharacters;

	@Override
	public String shortenedUrl(OriginalUrl originalUrl) throws InvalidUrlException {
		UrlValidator.validate(originalUrl.getUrl());
		String shortnedUrl = shortnerDao.getShortenedUrl(originalUrl.getUrl());
		if (shortnedUrl == null) {
			shortnedUrl = RandomStringGenerator.getRandomString(shortnedUrlLength, includeCharacters, includeNumbers);
			while (!shortnerDao.isAlreadyUsedShortUrl(shortnedUrl, originalUrl.getUrl()))
				shortnedUrl = RandomStringGenerator.getRandomString(shortnedUrlLength, includeCharacters, includeNumbers);
			
			shortnerDao.saveShortnedUrl(originalUrl.getUrl(), shortnedUrl);
			
		} 
		return shortnerStart + shortnedUrl;
		
	}

	@Override
	public String originalUrl(String url) throws InvalidUrlException {
		// TODO Will be used enhancement where we will have to implement redirection from shortened URL
		UrlValidator.validate(url);
		String shortURL = url.substring(url.indexOf(shortnerStart));
		String existingOriginalUrl = shortnerDao.getOriginalUrl(shortURL);
		if (existingOriginalUrl == null) {
			throw new InvalidUrlException("Original URL doesn't exists");
		} 
		return existingOriginalUrl;
	}

}
