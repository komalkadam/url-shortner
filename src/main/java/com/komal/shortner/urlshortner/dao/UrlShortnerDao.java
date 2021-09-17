/**
 * 
 */
package com.komal.shortner.urlshortner.dao;

/**
 * @author kkadam
 *
 */
public interface UrlShortnerDao {
	void saveShortnedUrl(String originalUrl, String shortUrl);
	
	String getOriginalUrl(String shortUrl);
	

}
