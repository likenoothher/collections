package com.foxminded.charcounter.cache;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KeyUseFrequencyListTest {
    KeyUseFrequencyList<String> keyUseFrequencyList;

    @Test
    public void whenRemoveLeastUsedRequest_thenRemoveLeastUsedKey() {
        keyUseFrequencyList = new KeyUseFrequencyList<>(5);
        keyUseFrequencyList.add("key1");
        keyUseFrequencyList.used("key1");
        keyUseFrequencyList.used("key1");
        keyUseFrequencyList.used("key1");

        keyUseFrequencyList.add("key2");
        keyUseFrequencyList.used("key2");
        keyUseFrequencyList.used("key2");

        keyUseFrequencyList.add("key3");
        keyUseFrequencyList.used("key3");
        keyUseFrequencyList.used("key3");
        keyUseFrequencyList.used("key3");
        keyUseFrequencyList.used("key3");

        String leastUsedKey = keyUseFrequencyList.removeLeastUsed();

        assertEquals("key2", leastUsedKey);

    }

    @Test
    public void whenRemoveLeastUsedRequestEqualsUsedKeyExist_thenRemoveFirstAddedFromEqualsUsed() {
        keyUseFrequencyList = new KeyUseFrequencyList<>(5);
        keyUseFrequencyList.add("key1");
        keyUseFrequencyList.used("key1");
        keyUseFrequencyList.used("key1");

        keyUseFrequencyList.add("key2");
        keyUseFrequencyList.used("key2");

        keyUseFrequencyList.add("key3");
        keyUseFrequencyList.used("key3");

        keyUseFrequencyList.add("key4");
        keyUseFrequencyList.used("key4");
        keyUseFrequencyList.used("key4");

        String leastUsedKey = keyUseFrequencyList.removeLeastUsed();

        assertEquals("key2", leastUsedKey);

    }
}
