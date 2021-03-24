package com.foxminded.charcounter.cache;

import java.util.*;

public class CharactersAmountCache<K, V> implements GenericCounterCache<K, V> {

    private final Map<K, V> cache;
    private final TimesUsedList<K> frequencyList;
    private final int capacity;

    public CharactersAmountCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        this.frequencyList = new TimesUsedList<>(capacity);
    }


    @Override
    public boolean contains(K key) {
        return cache.containsKey(key);
    }

    @Override
    public V get(K key) {
        if (!cache.containsKey(key)) {
            return null;
        }
        frequencyList.used(key);
        return cache.get(key);
    }

    @Override
    public V put(K key, V value) {
        if (cache.size() >= capacity) {
            K leastUsedKey = frequencyList.getLeastUsed();
            frequencyList.removeLeastUsed();
            cache.remove(leastUsedKey);
        }

        cache.putIfAbsent(key, value);
        frequencyList.add(key);

        return value;
    }

}
