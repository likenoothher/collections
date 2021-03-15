package com.foxminded;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InputsCacheStorage {

    private Map<String, Map> cache = new HashMap<String, Map>();

    public Map<String, Integer> getCachedInput(String key) {
        return Collections.unmodifiableMap(cache.get(key));
    }

    public boolean isInputExist(String key) {
        return cache.containsKey(key);
    }

    public void addToCache(String key, Map<String, Integer> map) {
        if (!cache.containsKey(key)) {
            cache.put(key, map);
        }
    }
}
