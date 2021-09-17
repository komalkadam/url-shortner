/**
 * 
 */
package com.komal.shortner.urlshortner.dao.impl;

import org.springframework.stereotype.Service;

import com.komal.shortner.urlshortner.dao.UrlShortnerDao;

/**
 * @author kkadam
 *
 */
@Service
public class IgniteBasedDao implements UrlShortnerDao {

	@Override
	public void saveShortnedUrl(String originalUrl, String shortUrl) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getOriginalUrl(String shortUrl) {
		// TODO Auto-generated method stub
		return null;
	}

}
