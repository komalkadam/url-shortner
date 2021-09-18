/**
 * 
 */
package com.komal.shortner.urlshortner.controller;

import java.io.UnsupportedEncodingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.komal.shortner.urlshortner.exception.InvalidUrlException;
import com.komal.shortner.urlshortner.model.OriginalUrl;
import com.komal.shortner.urlshortner.model.ShortnedUrl;
import com.komal.shortner.urlshortner.service.UrlShortnerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author kkadam
 *
 */
@RestController
@RequestMapping("/short")
@Api(value = "UrlShortnerController", description = "URL Shortner controller", consumes = "application/json", produces = "application/json")
public class UrlShortnerController {
	private static final Logger logger = LogManager.getLogger(UrlShortnerController.class);

	@Autowired
	UrlShortnerService urlShortnerService;


	@PostMapping
	@ResponseBody
	@ApiOperation(value = "Get shortned URL", notes = "Get shortned URL", response = OriginalUrl.class)
	public ShortnedUrl getShortnedUrl(@RequestBody OriginalUrl url) throws InvalidUrlException, UnsupportedEncodingException {
		logger.debug("Getting shortned URL");
		ShortnedUrl shortnedUrl = new ShortnedUrl();
		shortnedUrl.setShortnedUrl(urlShortnerService.shortenedUrl(url));
		return shortnedUrl;
	}
	
}
