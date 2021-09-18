/**
 * 
 */
package com.komal.shortner.urlshortner.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	private static final Logger logger = LogManager.getLogger(RandomStringGeneratorUrlShortnerService.class);
	
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
		logger.debug("Shortening the URL:" + originalUrl.getUrl());
		UrlValidator.validate(originalUrl.getUrl());
		logger.debug("Checking if shortened URL already exists:" + originalUrl.getUrl());
		String shortnedUrl = shortnerDao.getShortenedUrl(originalUrl.getUrl());
		logger.debug("Random short string:" + shortnedUrl);
		if (shortnedUrl == null) {
			logger.debug("Shortened URL doesn't exists:" + shortnedUrl);
			shortnedUrl = RandomStringGenerator.getRandomString(shortnedUrlLength, includeCharacters, includeNumbers);
			logger.debug("Check if random string is already used:" + shortnedUrl);
			while (!shortnerDao.isAlreadyUsedShortUrl(shortnedUrl, originalUrl.getUrl())) {
				logger.debug("Random string is already used:" + shortnedUrl);
				shortnedUrl = RandomStringGenerator.getRandomString(shortnedUrlLength, includeCharacters, includeNumbers);
				logger.debug("Generated new random string:" + shortnedUrl);
			}
			
			shortnerDao.saveShortnedUrl(originalUrl.getUrl(), shortnedUrl);
			
		} 
		return shortnerStart + shortnedUrl;
		
	}

	@Override
	public String originalUrl(String url) throws InvalidUrlException {
		// TODO Will be used enhancement where we will have to implement redirection from shortened URL
		UrlValidator.validate(url);
		logger.debug("Get original URL:" + url);
		String shortURL = url.substring(url.indexOf(shortnerStart));
		String existingOriginalUrl = shortnerDao.getOriginalUrl(shortURL);
		if (existingOriginalUrl == null) {
			logger.error("Original URL doesn't exists:" + url);
			throw new InvalidUrlException("Original URL doesn't exists");
		} 
		return existingOriginalUrl;
	}

}
