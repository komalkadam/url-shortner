/**
 * 
 */
package com.komal.shortner.urlshortner.validator;

import com.komal.shortner.urlshortner.exception.InvalidUrlException;

/**
 * @author kkadam
 *
 */
public class UrlValidator {

	public static void validate(String url) throws InvalidUrlException {
		org.apache.commons.validator.routines.UrlValidator validator = new org.apache.commons.validator.routines.UrlValidator();
		if (!validator.isValid(url)) {
			throw new InvalidUrlException("Invalid URL");
		}
	}
}
