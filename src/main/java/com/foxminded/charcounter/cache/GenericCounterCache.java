package com.foxminded.charcounter.cache;

public interface GenericCounterCache<K, V> {

    boolean contains(K key);

    V get(K key);

    V put(K key, V value);

}
