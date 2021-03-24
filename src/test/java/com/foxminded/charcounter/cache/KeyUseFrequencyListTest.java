package com.foxminded.charcounter.cache;

import org.junit.Test;

import static org.junit.Assert.*;

public class KeyUseFrequencyListTest {
    KeyUseFrequencyList<String> keyUseFrequencyList;

    @Test
    public void whenAddKey_thenKeysTimesUsedIsZero() {
        keyUseFrequencyList = new KeyUseFrequencyList<>(5);
        keyUseFrequencyList.add("key");
        long expectedTimeOfUse = keyUseFrequencyList.getFrequencyList().get(0);
        assertEquals(expectedTimeOfUse, 0L);
    }

    @Test
    public void whenAddExistedKey_thenKeyStandsWithPreviousValue() {
        keyUseFrequencyList = new KeyUseFrequencyList<>(5);
        keyUseFrequencyList.add("key");
        keyUseFrequencyList.used("key");
        keyUseFrequencyList.used("key");

        keyUseFrequencyList.add("key");
        long expectedTimeOfUse = keyUseFrequencyList.getFrequencyList().get(0);
        assertEquals(expectedTimeOfUse, 2L);
    }

    @Test
    public void whenAddSeveralDiffKeys_thenKeysTimesUsedIsZeroPerEach() {
        keyUseFrequencyList = new KeyUseFrequencyList<>(5);
        keyUseFrequencyList.add("key1");
        keyUseFrequencyList.add("key2");
        keyUseFrequencyList.add("key3");
        long expectedTimeOfUse = keyUseFrequencyList.getFrequencyList().get(0);
        assertEquals(expectedTimeOfUse, 0L);
        expectedTimeOfUse = keyUseFrequencyList.getFrequencyList().get(1);
        assertEquals(expectedTimeOfUse, 0L);
        expectedTimeOfUse = keyUseFrequencyList.getFrequencyList().get(2);
        assertEquals(expectedTimeOfUse, 0L);
    }

    @Test
    public void whenAddKeyAndUsedXTimes_thenKeysTimesUsedIsX() {
        keyUseFrequencyList = new KeyUseFrequencyList<>(5);
        keyUseFrequencyList.add("key");
        keyUseFrequencyList.used("key");
        keyUseFrequencyList.used("key");
        keyUseFrequencyList.used("key");
        long expectedTimeOfUse = keyUseFrequencyList.getFrequencyList().get(0);
        assertEquals(expectedTimeOfUse, 3L);
    }

    @Test
    public void whenAddXKeysAndUsedXTimes_thenKeysTimesUsedIsX() {
        keyUseFrequencyList = new KeyUseFrequencyList<>(5);
        keyUseFrequencyList.add("key1");
        keyUseFrequencyList.used("key1");
        keyUseFrequencyList.used("key1");
        keyUseFrequencyList.used("key1");
        long expectedTimeOfUse = keyUseFrequencyList.getFrequencyList().get(0);
        assertEquals(expectedTimeOfUse, 3L);
        keyUseFrequencyList.add("key2");
        keyUseFrequencyList.used("key2");
        keyUseFrequencyList.used("key2");
        expectedTimeOfUse = keyUseFrequencyList.getFrequencyList().get(1);
        assertEquals(expectedTimeOfUse, 2L);

    }

    @Test
    public void whenGetLeastTimeRequest_thenReturnLeastUsedKey() {
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

        String leastUsedKey = keyUseFrequencyList.getLeastUsed();
        assertEquals(leastUsedKey, "key2");

    }

    @Test
    public void whenRemoveLeastTimeRequest_thenRemoveLeastUsedKey() {
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

        keyUseFrequencyList.removeLeastUsed();

        assertEquals(2, keyUseFrequencyList.getFrequencyList().size());
        assertTrue(keyUseFrequencyList.getKeysList().contains("key1"));
        assertTrue(keyUseFrequencyList.getKeysList().contains("key3"));

        int indexOfKey1 = keyUseFrequencyList.getKeysList().indexOf("key1");
        int indexOfKey3 = keyUseFrequencyList.getKeysList().indexOf("key3");

        assertEquals(3, (long) keyUseFrequencyList.getFrequencyList().get(indexOfKey1));
        assertEquals(4, (long) keyUseFrequencyList.getFrequencyList().get(indexOfKey3));

    }
}
