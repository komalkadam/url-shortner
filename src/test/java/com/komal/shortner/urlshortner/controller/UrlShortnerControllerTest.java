package com.komal.shortner.urlshortner.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;

import com.komal.shortner.urlshortner.UrlshortnerApplication;
import com.komal.shortner.urlshortner.model.OriginalUrl;
import com.komal.shortner.urlshortner.model.ShortnedUrl;
import com.komal.shortner.urlshortner.service.UrlShortnerService;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { UrlshortnerApplication.class })
@AutoConfigureMockMvc( addFilters = false)
@WebMvcTest(UrlShortnerController.class)
class UrlShortnerControllerTest extends AbstractControllerTest {


	@MockBean
	UrlShortnerService urlShortnerService;
	
	
	
	@Test
	public void postActivityTest() throws Exception {
		OriginalUrl url = new OriginalUrl();
		url.setUrl("https://www.google.com/search?q=remove+element+k+linked+list&sxsrf="
				+ "AOaemvJB1rvMygrnAIVbxLyQAS9iYDvnNw%3A1631899176566&ei=KM5EYcr3IfXez7sPgqOIqAQ&oq="
				+ "remove+element+k+linked+list&gs_lcp=Cgdnd3Mtd2l6EAMyBAgAEA0yBAgAEA0yCAgAEAgQDRAeMggI"
				+ "ABAIEA0QHjoHCAAQRxCwAzoICAAQCBAHEB5KBQg8EgEySgQIQRgAUMkrWOU0YLw2aAJwAXgAgAGQAYgBugaS"
				+ "AQMwLjaYAQCgAQHIAQjAAQE&sclient=gws-wiz&ved=0ahUKEwjKq-mVwobzAhV173MBHYIRAkUQ4dUDCA8&uact=5");
		ShortnedUrl response = new ShortnedUrl();
		response.setShortnedUrl("http://komal.shortner/Q0YuTtM3WZFJ");
		when(urlShortnerService.shortenedUrl(ArgumentMatchers.any(OriginalUrl.class))).thenReturn(response.getShortnedUrl());
		MvcResult result = this.mvc.perform(post("/short").contentType(MediaType.APPLICATION_JSON)
				.content("{\"url\" : \""+ url.getUrl()+"\"}").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).
		andReturn();
		result.equals(response);
	}

}
