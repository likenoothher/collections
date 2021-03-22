package com.foxminded.charcounter.cache;

import java.util.*;

public class CharactersAmountCache<K, V> implements GenericCounterCache<K, V> {

    private Map<K, V> cache;
    private Map<K, Long> keyUsingFrequency;
    private final int capacity;

    public CharactersAmountCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        this.keyUsingFrequency = new HashMap<>();
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
        long timesUsed = keyUsingFrequency.get(key);
        keyUsingFrequency.put(key, timesUsed + 1);
        return cache.get(key);
    }

    @Override
    public V put(K key, V value) {
        if (cache.size() >= capacity) {
            Long minNumbersUses = Collections.min(keyUsingFrequency.values());
            List<K> mostUnusedCachesKeys = getKeysFromValue(keyUsingFrequency, minNumbersUses);

            cache.remove(mostUnusedCachesKeys.get(0));
            keyUsingFrequency.remove(mostUnusedCachesKeys.get(0));
        }
        cache.putIfAbsent(key, value);
        keyUsingFrequency.put(key, 0L);

        return value;
    }

    private List<K> getKeysFromValue(Map<K, Long> map, Long value) {
        List<K> list = new ArrayList<K>();
        for (K o : map.keySet()) {
            if (map.get(o).equals(value)) {
                list.add(o);
            }
        }
        return list;
    }
}
