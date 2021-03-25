package com.foxminded.charcounter.cache;

import java.util.HashMap;
import java.util.Map;

public class CharactersAmountCache implements GenericCounterCache<String, Map<Character, Long>> {

    private final Map<String, Map<Character, Long>> cache;
    private final KeyUseFrequencyList<String> frequencyList;
    private final int capacity;

    public CharactersAmountCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        this.frequencyList = new KeyUseFrequencyList<>(capacity);
    }

    @Override
    public boolean contains(String key) {
        return cache.containsKey(key);
    }

    @Override
    public Map<Character, Long> get(String key) {
        if (!cache.containsKey(key)) {
            return null;
        }
        frequencyList.used(key);
        return cache.get(key);
    }

    @Override
    public Map<Character, Long> put(String key, Map<Character, Long> value) {
        if (cache.size() >= capacity) {
            String leastUsedKey = frequencyList.removeLeastUsed();
            cache.remove(leastUsedKey);
        }
        cache.putIfAbsent(key, value);
        frequencyList.add(key);

        return value;
    }

}
