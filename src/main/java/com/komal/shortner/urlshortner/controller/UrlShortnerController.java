/**
 * 
 */
package com.komal.shortner.urlshortner.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.komal.shortner.urlshortner.exception.InvalidUrlException;
import com.komal.shortner.urlshortner.model.ShortnedUrl;
import com.komal.shortner.urlshortner.model.UrlShortner;
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
	@Autowired
	UrlShortnerService urlShortnerService;


	@PostMapping
	@ResponseBody
	@ApiOperation(value = "Save activity", notes = "Save activity", response = UrlShortner.class)
	public ShortnedUrl saveActivity(@RequestBody UrlShortner url) throws InvalidUrlException, UnsupportedEncodingException {
		ShortnedUrl shortnedUrl = new ShortnedUrl();
		shortnedUrl.setShortnedUrl(urlShortnerService.shortenedUrl(url.getUrl()));
		return shortnedUrl;
	}

}
