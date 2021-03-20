package com.foxminded.charcounter.cache;

import java.util.HashMap;
import java.util.Map;

public class CharactersAmountCache<K, V> implements GenericCounterCache<K, V> {

    private Map<K, V> cache = new HashMap<>();


    @Override
    public boolean contains(K key) {
        return cache.containsKey(key);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public V put(K key, V value) {
        cache.putIfAbsent(key, value);
        return value;
    }
}
