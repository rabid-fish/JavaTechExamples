package com.test.tiles.view;

import java.util.HashMap;
import java.util.Map;


public class TilesBoosterUriCache {

	private static Map<String, TilesBoosterUri> cache = new HashMap<String, TilesBoosterUri>();

	/**
	 * Grab a TilesBoosterUri from a singleton cache.
	 */
	public TilesBoosterUri pullUriFromCache(String uriPath, String contextPath) {
		
		if (cache.containsKey(uriPath)) {
			return cache.get(uriPath);
		}
		
		TilesBoosterUri uri;

		synchronized (cache) {
			// Never know when threads will back up
			if (cache.containsKey(uriPath)) {
				return cache.get(uriPath);
			}
			
			uri = TilesBoosterUri.constructUri(uriPath, contextPath);
			cache.put(uriPath, uri);
		}

		return uri;
	}
}
