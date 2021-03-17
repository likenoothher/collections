package com.foxminded;

import gnu.trove.map.TCharLongMap;

import java.util.HashMap;
import java.util.Map;

public class CharactersAmountCache {

    private Map<String, TCharLongMap> cache = new HashMap<String, TCharLongMap>();

    public TCharLongMap getCachedInput(String key) {
        return cache.get(key);
    }

    public boolean contains(String key) {
        return cache.containsKey(key);
    }

    public TCharLongMap putToCache(String key, TCharLongMap map) {
        cache.putIfAbsent(key, map);
        return map;
    }

}
