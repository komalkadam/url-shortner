/**
 * 
 */
package com.komal.shortner.urlshortner.dao.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.komal.shortner.urlshortner.dao.UrlShortnerDao;
import com.komal.shortner.urlshortner.model.OriginalUrl;

/**
 * @author kkadam
 *
 */
@Service
public class IgniteBasedDao implements UrlShortnerDao {
	private static final String ORIGINAL_TO_SHORT = "original_to_short";
	
	private static final String SHORT_TO_ORIGINAL = "short_to_original";

	@Autowired
	Ignite ignite;
	
	@Value("${com.komal.urlshortner.expiry}")
	private int expireShortnedURLInDays;
	
	private IgniteCache<String, String> shortToOriginal;
	
	private IgniteCache<String, String> originalToShort;
	
	
	@PostConstruct
	private void init() {
		shortToOriginal = getOrCreateCache(SHORT_TO_ORIGINAL);
		originalToShort = getOrCreateCache(ORIGINAL_TO_SHORT);
	}

	@Override
	public void saveShortnedUrl(String originalUrl, String shortUrl) {
		shortToOriginal.put(shortUrl, originalUrl);
		originalToShort.put(originalUrl, shortUrl);
	}

	@Override
	public String getOriginalUrl(String shortUrl) {
		String url = shortToOriginal.get(shortUrl);
		if (url != null)
			return url;
		return null;
	}
	
	@Override
	public boolean isAlreadyUsedShortUrl(String shortUrl, String url) {
		String existingURL = getOriginalUrl(url);
		if (existingURL != null)
			return !existingURL.equals(url);
		return true;
	}
	
	private IgniteCache<String, String> getOrCreateCache(String cacheName){
		CacheConfiguration<String, String> cacheConfigurationUrlShortner = new CacheConfiguration<String, String>();
		cacheConfigurationUrlShortner.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.DAYS, expireShortnedURLInDays)));
		cacheConfigurationUrlShortner.setName(cacheName);
		//cacheConfigurationUrlShortner.setIndexedTypes(String.class, OriginalUrl.class);
		return ignite.getOrCreateCache(cacheConfigurationUrlShortner);
	}

	@Override
	public String getShortenedUrl(String originalUrl) {
		return originalToShort.get(originalUrl);
	}

	

}
