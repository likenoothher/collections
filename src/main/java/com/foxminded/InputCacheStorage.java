package com.foxminded;

import gnu.trove.map.TCharLongMap;
import gnu.trove.map.hash.TCharLongHashMap;

import java.util.HashMap;
import java.util.Map;

public class InputCacheStorage {

    private Map<String, TCharLongMap> cache = new HashMap<String, TCharLongMap>();

    public TCharLongMap getCachedInput(String key) {
        return new TCharLongHashMap(cache.get(key));
    }

    public boolean isInputExist(String key) {
        return cache.containsKey(key);
    }

    public void addToCache(String key, TCharLongMap map) {
        if (!cache.containsKey(key)) {
            cache.put(key, map);
        }
    }
}
