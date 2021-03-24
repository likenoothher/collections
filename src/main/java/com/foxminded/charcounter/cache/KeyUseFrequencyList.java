package com.foxminded.charcounter.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KeyUseFrequencyList<K> {
    private final List<K> keysList;
    private final List<Long> frequencyList;

    public KeyUseFrequencyList(int capacity) {
        this.keysList = new ArrayList<>(capacity);
        this.frequencyList = new ArrayList<>(capacity);
    }

    public List<K> getKeysList() {
        return Collections.unmodifiableList(keysList);
    }

    public List<Long> getFrequencyList() {
        return Collections.unmodifiableList(frequencyList);
    }

    public void add(K key) {
        if (!keysList.contains(key)) {
            keysList.add(key);
            frequencyList.add(0L);
        }
    }

    public void used(K key) {
        if (keysList.contains(key)) {
            int index = keysList.indexOf(key);
            Long timesUsed = frequencyList.get(index);
            frequencyList.remove(index);
            frequencyList.add(index, timesUsed + 1);
        }
    }

    public K getLeastUsed() {
        int index = frequencyList.indexOf(Collections.min(frequencyList));
        return keysList.get(index);
    }

    public void removeLeastUsed() {
        int index = frequencyList.indexOf(Collections.min(frequencyList));
        keysList.remove(index);
        frequencyList.remove(index);
    }
}
