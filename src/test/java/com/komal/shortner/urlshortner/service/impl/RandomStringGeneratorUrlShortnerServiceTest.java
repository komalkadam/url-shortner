package com.komal.shortner.urlshortner.service.impl;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.komal.shortner.urlshortner.UrlshortnerApplication;
import com.komal.shortner.urlshortner.dao.UrlShortnerDao;
import com.komal.shortner.urlshortner.exception.InvalidUrlException;
import com.komal.shortner.urlshortner.model.OriginalUrl;
import com.komal.shortner.urlshortner.service.RandomStringGenerator;
import com.komal.shortner.urlshortner.service.UrlShortnerService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = UrlshortnerApplication.class)
class RandomStringGeneratorUrlShortnerServiceTest {

	@Autowired
	UrlShortnerService urlShortnerService;
	
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

	@MockBean
	UrlShortnerDao shortnerDao;

	@Value("${com.komal.urlshortner.short.url.start}")
	private String shortnerStart;

	@Value("${com.komal.urlshortner.short.url.length}")
	private int shortnedUrlLength;

	@Value("${com.komal.urlshortner.short.url.include.numbers}")
	private boolean includeNumbers;

	@Value("${com.komal.urlshortner.short.url.include.characters}")
	private boolean includeCharacters;
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();

	@Test
	void throwsExceptionForIncorrectURL() {
		OriginalUrl url = new OriginalUrl();
		url.setUrl("random");
		Exception exception = assertThrows(InvalidUrlException.class, () -> {
			urlShortnerService.shortenedUrl(url);
		});
		String expectedMessage = "Invalid URL";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void throwsExceptionForIncorrectShortURL() {
		Exception exception = assertThrows(InvalidUrlException.class, () -> {
			urlShortnerService.originalUrl("random_original");
		});
		String expectedMessage = "Invalid URL";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void shortenURLWithExistsInCache() {
		OriginalUrl url = new OriginalUrl();
		url.setUrl(ORIGINAL_URL);
		doNothing().when(shortnerDao).saveShortnedUrl(ArgumentMatchers.any(String.class), ArgumentMatchers.any(String.class));
		when(shortnerDao.getShortenedUrl(ArgumentMatchers.any(String.class))).thenReturn(SHORT_STRING);
		try {
			String shortURL = urlShortnerService.shortenedUrl(url);
			assertNotNull(shortURL);
			assertTrue(shortURL.startsWith(shortnerStart));
			assertTrue(shortURL.endsWith(SHORT_STRING));
		} catch (InvalidUrlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void shortenURLWithDoesNotExistsInCache() {
		OriginalUrl url = new OriginalUrl();
		url.setUrl(ORIGINAL_URL);
		doNothing().when(shortnerDao).saveShortnedUrl(ArgumentMatchers.any(String.class), ArgumentMatchers.any(String.class));
		when(shortnerDao.getShortenedUrl(ArgumentMatchers.any(String.class))).thenReturn(null);
		when(shortnerDao.isAlreadyUsedShortUrl(ArgumentMatchers.any(String.class), ArgumentMatchers.any(String.class))).thenReturn(true);
		try (MockedStatic<RandomStringGenerator> utilities = Mockito.mockStatic(RandomStringGenerator.class)) {
            utilities.when(() -> RandomStringGenerator.getRandomString(ArgumentMatchers.any(Integer.class), ArgumentMatchers.any(Boolean.class), ArgumentMatchers.any(Boolean.class)))
                .thenReturn(SHORT_STRING);
        }
		try {
			String shortURL = urlShortnerService.shortenedUrl(url);
			assertNotNull(shortURL);
			assertTrue(shortURL.startsWith(shortnerStart));
		} catch (InvalidUrlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
