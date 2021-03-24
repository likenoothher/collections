package com.foxminded.charcounter.cache;

import org.junit.Test;

import static org.junit.Assert.*;

public class TimesUsedListTest {
    TimesUsedList<String> timesUsedList;

    @Test
    public void whenAddKey_thenKeysTimesUsedIsZero() {
        timesUsedList = new TimesUsedList<>(5);
        timesUsedList.add("key");
        long expectedTimeOfUse = timesUsedList.getFrequencyList().get(0);
        assertEquals(expectedTimeOfUse, 0L);
    }

    @Test
    public void whenAddExistedKey_thenKeyStandsWithPreviousValue() {
        timesUsedList = new TimesUsedList<>(5);
        timesUsedList.add("key");
        timesUsedList.used("key");
        timesUsedList.used("key");

        timesUsedList.add("key");
        long expectedTimeOfUse = timesUsedList.getFrequencyList().get(0);
        assertEquals(expectedTimeOfUse, 2L);
    }

    @Test
    public void whenAddSeveralDiffKeys_thenKeysTimesUsedIsZeroPerEach() {
        timesUsedList = new TimesUsedList<>(5);
        timesUsedList.add("key1");
        timesUsedList.add("key2");
        timesUsedList.add("key3");
        long expectedTimeOfUse = timesUsedList.getFrequencyList().get(0);
        assertEquals(expectedTimeOfUse, 0L);
        expectedTimeOfUse = timesUsedList.getFrequencyList().get(1);
        assertEquals(expectedTimeOfUse, 0L);
        expectedTimeOfUse = timesUsedList.getFrequencyList().get(2);
        assertEquals(expectedTimeOfUse, 0L);
    }

    @Test
    public void whenAddKeyAndUsedXTimes_thenKeysTimesUsedIsX() {
        timesUsedList = new TimesUsedList<>(5);
        timesUsedList.add("key");
        timesUsedList.used("key");
        timesUsedList.used("key");
        timesUsedList.used("key");
        long expectedTimeOfUse = timesUsedList.getFrequencyList().get(0);
        assertEquals(expectedTimeOfUse, 3L);
    }

    @Test
    public void whenAddXKeysAndUsedXTimes_thenKeysTimesUsedIsX() {
        timesUsedList = new TimesUsedList<>(5);
        timesUsedList.add("key1");
        timesUsedList.used("key1");
        timesUsedList.used("key1");
        timesUsedList.used("key1");
        long expectedTimeOfUse = timesUsedList.getFrequencyList().get(0);
        assertEquals(expectedTimeOfUse, 3L);
        timesUsedList.add("key2");
        timesUsedList.used("key2");
        timesUsedList.used("key2");
        expectedTimeOfUse = timesUsedList.getFrequencyList().get(1);
        assertEquals(expectedTimeOfUse, 2L);

    }

    @Test
    public void whenGetLeastTimeRequest_thenReturnLeastUsedKey() {
        timesUsedList = new TimesUsedList<>(5);
        timesUsedList.add("key1");
        timesUsedList.used("key1");
        timesUsedList.used("key1");
        timesUsedList.used("key1");

        timesUsedList.add("key2");
        timesUsedList.used("key2");
        timesUsedList.used("key2");

        timesUsedList.add("key3");
        timesUsedList.used("key3");
        timesUsedList.used("key3");
        timesUsedList.used("key3");
        timesUsedList.used("key3");

        String leastUsedKey = timesUsedList.getLeastUsed();
        assertEquals(leastUsedKey, "key2");

    }

    @Test
    public void whenRemoveLeastTimeRequest_thenRemoveLeastUsedKey() {
        timesUsedList = new TimesUsedList<>(5);
        timesUsedList.add("key1");
        timesUsedList.used("key1");
        timesUsedList.used("key1");
        timesUsedList.used("key1");

        timesUsedList.add("key2");
        timesUsedList.used("key2");
        timesUsedList.used("key2");

        timesUsedList.add("key3");
        timesUsedList.used("key3");
        timesUsedList.used("key3");
        timesUsedList.used("key3");
        timesUsedList.used("key3");

        timesUsedList.removeLeastUsed();

        assertEquals(2, timesUsedList.getFrequencyList().size());
        assertTrue(timesUsedList.getKeysList().contains("key1"));
        assertTrue(timesUsedList.getKeysList().contains("key3"));

        int indexOfKey1 = timesUsedList.getKeysList().indexOf("key1");
        int indexOfKey3 = timesUsedList.getKeysList().indexOf("key3");

        assertEquals(3, (long) timesUsedList.getFrequencyList().get(indexOfKey1));
        assertEquals(4, (long) timesUsedList.getFrequencyList().get(indexOfKey3));

    }
}
