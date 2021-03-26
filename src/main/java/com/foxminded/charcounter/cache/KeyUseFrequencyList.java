package com.foxminded.charcounter.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KeyUseFrequencyList<K> {
    private final List<K> keysList;
    private final List<Long> frequencyList;
    private final int capacity;

    public KeyUseFrequencyList(int capacity) {
        this.capacity = capacity;
        this.keysList = new ArrayList<>(capacity);
        this.frequencyList = new ArrayList<>(capacity);
    }

    public void add(K key) {
        if (!keysList.contains(key)) {
            if (keysList.size() >= capacity) {
                removeLeastUsed();
            }
            keysList.add(key);
            frequencyList.add(0L);
        }
    }

    public void used(K key) {
        if (keysList.contains(key)) {
            int index = keysList.indexOf(key);
            Long timesUsed = frequencyList.get(index);
            frequencyList.set(index, timesUsed + 1);
        }
    }

    public K removeLeastUsed() {
        int index = frequencyList.indexOf(Collections.min(frequencyList));
        K removedKey = keysList.get(index);
        keysList.remove(index);
        frequencyList.remove(index);
        return removedKey;
    }

}
