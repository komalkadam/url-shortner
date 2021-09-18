/**
 * 
 */
package com.komal.shortner.urlshortner.model;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

/**
 * @author kkadam
 *
 */
public class OriginalUrl {
	@QuerySqlField(index = true)
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "{url:" + url + "}";
	}
	

}
