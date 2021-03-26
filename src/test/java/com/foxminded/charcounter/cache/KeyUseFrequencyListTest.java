package com.foxminded.charcounter.cache;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class KeyUseFrequencyListTest {
    KeyUseFrequencyList<String> keyUseFrequencyList;

    @Test
    public void whenNotExistedKeyAddedToList_thenKeyIsSavedWithZeroFrequencyValue() {
        keyUseFrequencyList = new KeyUseFrequencyList<>(2);
        keyUseFrequencyList.add("newKey");
        keyUseFrequencyList.add("usedOneTimeKey");
        keyUseFrequencyList.used("usedOneTimeKey");
        String removedKey = keyUseFrequencyList.removeLeastUsed();
        assertEquals("newKey", removedKey);
    }

    @Test
    public void whenExistedKeyAddedToList_thenKeyFrequencyValueStaysSame() {
        keyUseFrequencyList = new KeyUseFrequencyList<>(2);
        keyUseFrequencyList.add("newKey");
        keyUseFrequencyList.add("usedKey");

        keyUseFrequencyList.used("newKey");
        keyUseFrequencyList.used("usedKey");
        keyUseFrequencyList.used("usedKey");
        keyUseFrequencyList.add("usedKey");

        String removedKey = keyUseFrequencyList.removeLeastUsed();
        assertEquals("newKey", removedKey);
    }

    @Test
    public void whenNullKeyAddedToListFirstTime_thenNullKeyIsSavedWithZeroFrequencyValue() {
        keyUseFrequencyList = new KeyUseFrequencyList<>(2);
        keyUseFrequencyList.add(null);
        keyUseFrequencyList.add("usedOneTimeKey");
        keyUseFrequencyList.used("usedOneTimeKey");
        String removedKey = keyUseFrequencyList.removeLeastUsed();
        assertNull(removedKey);
    }

    @Test
    public void whenNullKeyAddedToListNotTheFirstTime_thenNullKeyFrequencyValueStaysSame() {
        keyUseFrequencyList = new KeyUseFrequencyList<>(2);
        keyUseFrequencyList.add(null);
        keyUseFrequencyList.add("usedKey");

        keyUseFrequencyList.used(null);
        keyUseFrequencyList.used("usedKey");
        keyUseFrequencyList.used("usedKey");
        keyUseFrequencyList.add("usedKey");

        String removedKey = keyUseFrequencyList.removeLeastUsed();
        assertNull(removedKey);
    }

    @Test
    public void whenEmptyStringKeyAddedToList_thenEmptyStringKeyIsSavedWithZeroFrequencyValue() {
        keyUseFrequencyList = new KeyUseFrequencyList<>(2);
        keyUseFrequencyList.add("");
        keyUseFrequencyList.add("usedOneTimeKey");
        keyUseFrequencyList.used("usedOneTimeKey");
        String removedKey = keyUseFrequencyList.removeLeastUsed();
        assertEquals("", removedKey);
    }

    @Test
    public void whenAddedNotExistedKeyToFullList_thenRemovedLeastUsedElement() {
        keyUseFrequencyList = new KeyUseFrequencyList<>(2);
        keyUseFrequencyList.add("firstKey");
        keyUseFrequencyList.used("firstKey");
        keyUseFrequencyList.add("secondKey");
        keyUseFrequencyList.add("thirdKey");
        String removedKey = keyUseFrequencyList.removeLeastUsed();
        assertEquals("thirdKey", removedKey);
        removedKey = keyUseFrequencyList.removeLeastUsed();
        assertEquals("firstKey", removedKey);
    }

    @Test
    public void whenAddedExistedKeyToFullList_thenThereIsNotAnyAffectToList() {
        keyUseFrequencyList = new KeyUseFrequencyList<>(2);
        keyUseFrequencyList.add("firstKey");
        keyUseFrequencyList.used("firstKey");
        keyUseFrequencyList.add("secondKey");
        keyUseFrequencyList.add("firstKey");
        String removedKey = keyUseFrequencyList.removeLeastUsed();
        assertEquals("secondKey", removedKey);
        removedKey = keyUseFrequencyList.removeLeastUsed();
        assertEquals("firstKey", removedKey);
    }

    @Test
    public void whenListReceivesGetRequestExistedKey_thenIncreaseKeyValueBy1() {
        keyUseFrequencyList = new KeyUseFrequencyList<>(3);
        keyUseFrequencyList.add("firstKey");
        keyUseFrequencyList.add("secondKey");
        keyUseFrequencyList.add("thirdKey");
        keyUseFrequencyList.used("firstKey");
        keyUseFrequencyList.used("firstKey");
        keyUseFrequencyList.used("secondKey");

        String removedKey = keyUseFrequencyList.removeLeastUsed();
        assertEquals("thirdKey", removedKey);
        removedKey = keyUseFrequencyList.removeLeastUsed();
        assertEquals("secondKey", removedKey);
    }

    @Test
    public void whenListReceivesGetRequestNotExistedKey_thenThereIsNotAnyAffectToAnyValues() {
        keyUseFrequencyList = new KeyUseFrequencyList<>(2);
        keyUseFrequencyList.add("firstKey");
        keyUseFrequencyList.add("secondKey");
        keyUseFrequencyList.used("firstKey");
        keyUseFrequencyList.used("firstKey");
        keyUseFrequencyList.used("secondKey");

        keyUseFrequencyList.used("notExistedKey");
        keyUseFrequencyList.used("notExistedKey");
        keyUseFrequencyList.used("notExistedKey");

        String removedKey = keyUseFrequencyList.removeLeastUsed();
        assertEquals("secondKey", removedKey);
    }

    @Test
    public void whenListReceivesGetRequestExistedNullKey_thenIncreaseNullKeyValueBy1() {
        keyUseFrequencyList = new KeyUseFrequencyList<>(3);
        keyUseFrequencyList.add(null);
        keyUseFrequencyList.add("secondKey");
        keyUseFrequencyList.add("thirdKey");
        keyUseFrequencyList.used(null);
        keyUseFrequencyList.used(null);
        keyUseFrequencyList.used("secondKey");

        String removedKey = keyUseFrequencyList.removeLeastUsed();
        assertEquals("thirdKey", removedKey);
        removedKey = keyUseFrequencyList.removeLeastUsed();
        assertEquals("secondKey", removedKey);
    }

    @Test
    public void whenListReceivesGetRequestNotExistedNullKey_thenThereIsNotAnyAffectToAnyValues() {
        keyUseFrequencyList = new KeyUseFrequencyList<>(2);
        keyUseFrequencyList.add("firstKey");
        keyUseFrequencyList.add("secondKey");
        keyUseFrequencyList.used("firstKey");
        keyUseFrequencyList.used("firstKey");
        keyUseFrequencyList.used("secondKey");

        keyUseFrequencyList.used(null);
        keyUseFrequencyList.used(null);
        keyUseFrequencyList.used(null);

        String removedKey = keyUseFrequencyList.removeLeastUsed();
        assertEquals("secondKey", removedKey);
    }

    @Test
    public void whenRemoveLeastUsedRequestInEmptyList_thenNSEException() {
        keyUseFrequencyList = new KeyUseFrequencyList<>(5);
        assertThrows(NoSuchElementException.class, () -> keyUseFrequencyList.removeLeastUsed());

    }

    @Test
    public void whenRemoveLeastUsedRequest_thenRemoveLeastUsedKeyAndReturnKey() {
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
    public void whenRemoveRequestAfterSeveralAddingOverCapacity_thenRemoveLeastUsedKeyFromPresentedKeys() {
        keyUseFrequencyList = new KeyUseFrequencyList<>(3);
        keyUseFrequencyList.add("key1");
        keyUseFrequencyList.used("key1");
        keyUseFrequencyList.used("key1");

        keyUseFrequencyList.add("key2");

        keyUseFrequencyList.add("key3");
        keyUseFrequencyList.used("key3");
        keyUseFrequencyList.used("key3");
        keyUseFrequencyList.used("key3");
        keyUseFrequencyList.used("key3");

        keyUseFrequencyList.add("key4");
        keyUseFrequencyList.used("key4");
        keyUseFrequencyList.used("key4");
        keyUseFrequencyList.used("key4");

        keyUseFrequencyList.add("key5");
        keyUseFrequencyList.used("key5");
        keyUseFrequencyList.used("key5");


        String leastUsedKey = keyUseFrequencyList.removeLeastUsed();
        assertEquals("key5", leastUsedKey);

        leastUsedKey = keyUseFrequencyList.removeLeastUsed();
        assertEquals("key4", leastUsedKey);

    }

    @Test
    public void whenRemoveRequestAfterSeveralAddingOverCapacityIncludeNull_thenRemoveLeastUsedKeyFromPresentedKeys() {
        keyUseFrequencyList = new KeyUseFrequencyList<>(3);
        keyUseFrequencyList.add("key1");
        keyUseFrequencyList.used("key1");
        keyUseFrequencyList.used("key1");

        keyUseFrequencyList.add("key2");
        keyUseFrequencyList.used("key2");

        keyUseFrequencyList.add("key3");
        keyUseFrequencyList.used("key3");
        keyUseFrequencyList.used("key3");
        keyUseFrequencyList.used("key3");
        keyUseFrequencyList.used("key3");

        keyUseFrequencyList.add(null);
        keyUseFrequencyList.used(null);
        keyUseFrequencyList.used(null);
        keyUseFrequencyList.used(null);

        keyUseFrequencyList.add("key5");
        keyUseFrequencyList.used("key5");
        keyUseFrequencyList.used("key5");

        String leastUsedKey = keyUseFrequencyList.removeLeastUsed();
        assertEquals("key5", leastUsedKey);

        leastUsedKey = keyUseFrequencyList.removeLeastUsed();
        assertNull(leastUsedKey);

    }


}
