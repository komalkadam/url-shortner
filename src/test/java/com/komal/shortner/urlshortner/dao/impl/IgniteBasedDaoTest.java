package com.komal.shortner.urlshortner.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.komal.shortner.urlshortner.UrlshortnerApplication;
import com.komal.shortner.urlshortner.dao.UrlShortnerDao;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = UrlshortnerApplication.class)
class IgniteBasedDaoTest {
	
	private static final String SHORT_STRING = "Q0YuTtM3WZFJ";
	private static final String ORIGINAL_URL = "https://www.google.com/search?"
			+ "q=apache+commons+validator+maven&sxsrf=AOaemvIRMHWSMSYxSEadzs"
			+ "_H4DGRrrT3Ig%3A1631906596152&ei=JOtEYc3lCOfvz7sP"
			+ "-NOd4Ag&oq=apache+commons+validator+maven&gs_lcp="
			+ "Cgdnd3Mtd2l6EAMyBAgAEEMyBggAEBYQHjIGCAAQFhAeMgYIA"
			+ "BAWEB4yBggAEBYQHjIGCAAQFhAeOgcIABBHELADOgcIABCwAxBDO"
			+ "goIABCABBCHAhAUOgUIABCABEoFCDwSATFKBAhBGABQqRVY7x5goy"
			+ "JoAXACeACAAZEBiAGLBpIBAzAuNpgBAKABAcgBCcABAQ&sclient=g"
			+ "ws-wiz&ved=0ahUKEwjNh-Hn3YbzAhXn93MBHfhpB4wQ4dUDCA4&uact=5";
	@Autowired 
	UrlShortnerDao dao;
	
	@Test
	void shouldReturnNullIfURLIsNotSaved() {
		String originalString = dao.getOriginalUrl("randomString");
		assertNull(originalString);
	}
	
	@Test
	void shouldReturnNullShortURlIfURLIsNotSaved() {
		String originalString = dao.getShortenedUrl("randomString");
		assertNull(originalString);
	}
	
	@Test
	void checkIfShortenURLIsSame() {
		dao.saveShortnedUrl(ORIGINAL_URL, SHORT_STRING);
		String shortURL = dao.getShortenedUrl(ORIGINAL_URL);
		assertEquals(shortURL, SHORT_STRING);
	}
	
	@Test
	void getOriginalUrlFromShortUrl() {
		dao.saveShortnedUrl(ORIGINAL_URL, SHORT_STRING);
		String originalString = dao.getOriginalUrl(SHORT_STRING);
		assertNotNull(originalString);
		assertEquals(originalString, ORIGINAL_URL);
	}
	
	@Test
	void shouldReturnSameUrl() {
		dao.saveShortnedUrl(ORIGINAL_URL, SHORT_STRING);
		String short1 = dao.getShortenedUrl(ORIGINAL_URL);
		dao.saveShortnedUrl(ORIGINAL_URL, SHORT_STRING);
		String short2 = dao.getShortenedUrl(ORIGINAL_URL);
		assertNotNull(short1);
		assertNotNull(short2);
		assertEquals(short1, short2);
	}
	
	@Test
	void checkIfShortURLAlreadyExists() {
		dao.saveShortnedUrl(ORIGINAL_URL, SHORT_STRING);
		boolean exists = dao.isAlreadyUsedShortUrl(SHORT_STRING, ORIGINAL_URL);
		assertTrue(exists);
	}
	
	
	@Test
	void checkIfShortURLDoesNotExists() {
		boolean exists = dao.isAlreadyUsedShortUrl("random_string", "random_string");
		assertTrue(exists);
	}

}
