package com.foxminded;

import java.util.HashMap;
import java.util.Map;

public class CacheInputsStorage {

    private Map<String, Map> cache = new HashMap<String, Map>();

    public Map<String, Map> getCache() {
        return cache;
    }

    public Map<String, Integer> getValues(String key) {
        return cache.get(key);
    }

    public boolean isInputExist(String key) {
        return cache.containsKey(key);
    }

    public void addToCache(String key, Map<String,Integer> map) {
        cache.put(key,map);
    }
}
