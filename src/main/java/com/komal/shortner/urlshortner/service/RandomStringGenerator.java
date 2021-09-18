/**
 * 
 */
package com.komal.shortner.urlshortner.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author kkadam
 *
 */
public class RandomStringGenerator {
	private static final Logger logger = LogManager.getLogger(RandomStringGenerator.class);
    public static String getRandomString(int length, boolean useLetters, boolean useNumbers) {
    	logger.debug("Get Random String");
    	return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    
}
