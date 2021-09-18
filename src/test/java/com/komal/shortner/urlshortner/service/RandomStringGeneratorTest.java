package com.komal.shortner.urlshortner.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class RandomStringGeneratorTest {

	@Test
	void checkIfRandomStringContainsOnlyAlphabets() {
		String randomString = RandomStringGenerator.getRandomString(11, true, false);
		assertNotNull(randomString);
		assertTrue(randomString.length() == 11);
		assertTrue(randomString.matches("^[a-zA-Z]*$"));
	}
	
	@Test
	void checkIfRandomStringContainsOnlyNumerals() {
		String randomString = RandomStringGenerator.getRandomString(11, false, true);
		assertNotNull(randomString);
		assertTrue(randomString.length() == 11);
		assertTrue(randomString.matches("^[0-9]+"));
	}
	
	@Test
	void checkIfRandomStringContainsBoth() {
		String randomString = RandomStringGenerator.getRandomString(11, true, true);
		assertNotNull(randomString);
		assertTrue(randomString.length() == 11);
		assertTrue(randomString.matches("^[a-zA-Z0-9]*$"));
	}

}
