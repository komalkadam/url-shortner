/**
 * 
 */
package com.komal.shortner.urlshortner.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.komal.shortner.urlshortner.exception.InvalidUrlException;

/**
 * @author kkadam
 *
 */
public class UrlValidator {
	private static final Logger logger = LogManager.getLogger(UrlValidator.class);

	public static void validate(String url) throws InvalidUrlException {
		logger.debug("Validating the URL:" + url);
		org.apache.commons.validator.routines.UrlValidator validator = new org.apache.commons.validator.routines.UrlValidator();
		if (!validator.isValid(url)) {
			logger.debug("Invalid URL:" + url);
			throw new InvalidUrlException("Invalid URL");
		}
	}
}
