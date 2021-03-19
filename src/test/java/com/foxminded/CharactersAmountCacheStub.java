package com.foxminded;

public class CharactersAmountCacheStub<K, V> implements GenericCounterCache<K, V> {

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return value;
    }
}
