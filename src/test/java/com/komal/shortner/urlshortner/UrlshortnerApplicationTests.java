package com.komal.shortner.urlshortner;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.komal.shortner.urlshortner.controller.UrlShortnerController;

@SpringBootTest
class UrlshortnerApplicationTests {

	@Autowired
	private UrlShortnerController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
