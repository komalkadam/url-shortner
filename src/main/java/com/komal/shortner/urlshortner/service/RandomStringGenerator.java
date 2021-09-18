/**
 * 
 */
package com.komal.shortner.urlshortner.service;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author kkadam
 *
 */
public class RandomStringGenerator {

    public static String getRandomString(int length, boolean useLetters, boolean useNumbers) {
    	return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    
}
