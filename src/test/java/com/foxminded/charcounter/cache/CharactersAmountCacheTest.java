package com.foxminded.charcounter.cache;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


public class CharactersAmountCacheTest {

    @Test
    public void whenCacheHasString_thenReturnTrue() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache(2);
        Map<Character, Long> mapOf11123Sequence = new HashMap<>();

        cache.put("11123", mapOf11123Sequence);

        assertTrue(cache.contains("11123"));
    }

    @Test
    public void whenCacheHasNotString_thenReturnFalse() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache(2);
        Map<Character, Long> mapOf11123Sequence = new HashMap<>();

        cache.put("11123", mapOf11123Sequence);

        assertFalse(cache.contains("23"));
    }

    @Test
    public void whenCacheHasNotNullKey_thenReturnFalse() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache(2);

        assertFalse(cache.contains(null));
    }

    @Test
    public void whenCacheHasNullKey_thenReturnTrue() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache(2);
        cache.put(null, null);

        assertTrue(cache.contains(null));
    }

    @Test
    public void whenCacheHasNotEmptyStringKey_thenReturnFalse() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache(2);

        assertFalse(cache.contains(""));
    }

    @Test
    public void whenCacheHasEmptyStringKey_thenReturnTrue() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache(2);
        cache.put("", null);

        assertTrue(cache.contains(""));
    }

    @Test
    public void whenGetKeyEqualsExistedInCache_thenReturnSymbolsNumberMap() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache(2);
        Map<Character, Long> mapOf11123Sequence = new HashMap<>();
        mapOf11123Sequence.put('1', 3L);
        mapOf11123Sequence.put('2', 1L);
        mapOf11123Sequence.put('3', 1L);
        cache.put("11123", mapOf11123Sequence);

        Map<Character, Long> expectedMap = new HashMap<>();
        expectedMap.put('1', 3L);
        expectedMap.put('2', 1L);
        expectedMap.put('3', 1L);

        assertEquals(expectedMap, cache.get("11123"));
    }

    @Test
    public void whenGetNotExistedKeyRequest_thenReturnNull() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache(2);

        assertNull(cache.get("not_existed_key"));
    }

    @Test
    public void whenGetNullKeyExistedInCacheRequest_thenReturnSymbolsNumberMap() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache(2);
        Map<Character, Long> mapOf11123Sequence = new HashMap<>();
        mapOf11123Sequence.put('1', 3L);
        mapOf11123Sequence.put('2', 1L);
        mapOf11123Sequence.put('3', 1L);
        cache.put(null, mapOf11123Sequence);

        Map<Character, Long> expectedMap = new HashMap<>();
        expectedMap.put('1', 3L);
        expectedMap.put('2', 1L);
        expectedMap.put('3', 1L);


        assertEquals(expectedMap, cache.get(null));
    }

    @Test
    public void whenGetNotExistedNullKeyRequest_thenReturnNull() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache(2);

        assertNull(cache.get(null));
    }

    @Test
    public void whenPutNewKey_thenSaveValueAndReturnIt() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache(2);
        Map<Character, Long> mapOf11123Sequence = new HashMap<>();
        mapOf11123Sequence.put('1', 3L);
        mapOf11123Sequence.put('2', 1L);
        mapOf11123Sequence.put('3', 1L);
        cache.put("11123", mapOf11123Sequence);

        Map<Character, Long> expectedMap = new HashMap<>();
        expectedMap.put('1', 3L);
        expectedMap.put('2', 1L);
        expectedMap.put('3', 1L);

        assertEquals(expectedMap, cache.put("11123", mapOf11123Sequence));
        assertTrue(cache.contains("11123"));
    }

    @Test
    public void whenPutExistedKey_thenWillNotSaveValueUnderThisKeyReturnCurrentKeysValue() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache(2);
        Map<Character, Long> mapOf11123Sequence = new HashMap<>();
        mapOf11123Sequence.put('1', 3L);
        mapOf11123Sequence.put('2', 1L);
        mapOf11123Sequence.put('3', 1L);

        Map<Character, Long> mapOf432Sequence = new HashMap<>();
        mapOf432Sequence.put('4', 1L);
        mapOf432Sequence.put('3', 1L);
        mapOf432Sequence.put('2', 1L);

        cache.put("11123", mapOf11123Sequence);

        assertEquals(mapOf11123Sequence, cache.put("11123", mapOf432Sequence));

    }

    @Test
    public void whenPutNullKeyFirstTime_thenSaveValueAndReturnIt() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache(2);
        Map<Character, Long> mapOf11123Sequence = new HashMap<>();
        mapOf11123Sequence.put('1', 3L);
        mapOf11123Sequence.put('2', 1L);
        mapOf11123Sequence.put('3', 1L);

        Map<Character, Long> expectedMap = new HashMap<>();
        expectedMap.put('1', 3L);
        expectedMap.put('2', 1L);
        expectedMap.put('3', 1L);

        assertEquals(expectedMap, cache.put(null, mapOf11123Sequence));
    }

    @Test
    public void whenPutNullKeyNotTheFirstTime_thenWillNotSaveValueUnderNullKeyReturnCurrentKeysValue() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache(2);
        Map<Character, Long> mapOf11123Sequence = new HashMap<>();
        mapOf11123Sequence.put('1', 3L);
        mapOf11123Sequence.put('2', 1L);
        mapOf11123Sequence.put('3', 1L);

        Map<Character, Long> mapOf432Sequence = new HashMap<>();
        mapOf432Sequence.put('4', 1L);
        mapOf432Sequence.put('3', 1L);
        mapOf432Sequence.put('2', 1L);

        Map<Character, Long> expectedMap = new HashMap<>();
        expectedMap.put('1', 3L);
        expectedMap.put('2', 1L);
        expectedMap.put('3', 1L);

        cache.put(null, mapOf11123Sequence);
        cache.put(null, mapOf432Sequence);

        assertEquals(expectedMap, cache.put(null, mapOf432Sequence));
    }

    @Test
    public void whenPutKeyWithNullValue_thenGetThisKeyRequestReturnsNull() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache(2);
        Map<Character, Long> mapOf11123Sequence = null;

        cache.put("11123", mapOf11123Sequence);

        assertNull(cache.get("11123"));
    }

    @Test
    public void whenPutExistedKeyWithNotNullValueAndCurrentValueIsNull_thenThisKeyIsAssociatedWithNewValue() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache(2);
        Map<Character, Long> nullMap = null;

        Map<Character, Long> mapOf11123Sequence = new HashMap<>();
        mapOf11123Sequence.put('1', 3L);
        mapOf11123Sequence.put('2', 1L);
        mapOf11123Sequence.put('3', 1L);

        Map<Character, Long> expectedMap = new HashMap<>();
        expectedMap.put('1', 3L);
        expectedMap.put('2', 1L);
        expectedMap.put('3', 1L);

        cache.put("11123", nullMap);

        assertEquals(expectedMap, cache.put("11123", mapOf11123Sequence));

    }

    @Test
    public void whenElementEvictedFromCacheAndAddedAgain_thenLeastUsedCacheElementIsEvictedForAddedAgainElement() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache(2);
        Map<Character, Long> mapOf11123Sequence = new HashMap<>();

        Map<Character, Long> mapOf432Sequence = new HashMap<>();

        Map<Character, Long> mapOf567Sequence = new HashMap<>();

        cache.put("11123", mapOf11123Sequence);
        cache.get("11123");
        cache.put("432", mapOf432Sequence);
        cache.put("567", mapOf567Sequence);

        assertFalse(cache.contains("432"));

        cache.put("432", mapOf432Sequence);

        assertTrue(cache.contains("432"));
        assertTrue(cache.contains("11123"));
        assertFalse(cache.contains("567"));

    }

    @Test
    public void whenCacheIsFull_thenEvictLeastUsedElements() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache(4);
        for (int i = 0; i < 4; i++) {
            cache.put(i + "", new HashMap<>());
        }

        for (int i = 4; i < 8; i++) {
            cache.put(i + "", new HashMap<>());
        }

        assertTrue(cache.contains("4"));
        assertTrue(cache.contains("5"));
        assertTrue(cache.contains("6"));
        assertTrue(cache.contains("7"));

        assertFalse(cache.contains("0"));
        assertFalse(cache.contains("1"));
        assertFalse(cache.contains("2"));
        assertFalse(cache.contains("3"));
    }

    @Test
    public void whenCacheIsFullOldElementsUsedMoreThanNew_thenRemoveLeastUsedNewElements() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache(5);
        for (int i = 0; i < 4; i++) {
            cache.put(i + "", new HashMap<>());
            cache.get(i + "");
        }

        for (int i = 4; i < 7; i++) {
            cache.put(i + "", new HashMap<>());
        }

        assertTrue(cache.contains("0"));
        assertTrue(cache.contains("1"));
        assertTrue(cache.contains("2"));
        assertTrue(cache.contains("3"));
        assertTrue(cache.contains("6"));

        assertFalse(cache.contains("4"));
        assertFalse(cache.contains("5"));

    }

    @Test
    public void whenCacheIsFullPartOfOldElementsUsedMoreThanNew_thenRemoveLeastUsedNewAndOldElements() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache(5);
        for (int i = 0; i < 4; i++) {
            cache.put(i + "", new HashMap<>());
            if (i % 2 == 0) {
                cache.get(i + "");
            }
        }

        for (int i = 4; i < 9; i++) {
            cache.put(i + "", new HashMap<>());
            if (i % 2 == 0) {
                cache.get(i + "");
            }
        }

        assertTrue(cache.contains("0"));
        assertTrue(cache.contains("2"));
        assertTrue(cache.contains("4"));
        assertTrue(cache.contains("6"));
        assertTrue(cache.contains("8"));

        assertFalse(cache.contains("1"));
        assertFalse(cache.contains("3"));
        assertFalse(cache.contains("5"));
        assertFalse(cache.contains("7"));

    }

    @Test
    public void whenCacheIsFullAndAddingSeveralNull_thenEvictOneLeastUsedElementsForFirstNull() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache(4);
        cache.put("0", new HashMap<>());
        cache.put("1", new HashMap<>());
        cache.get("1");
        cache.put("2", new HashMap<>());
        cache.get("2");
        cache.put("3", new HashMap<>());
        cache.get("3");


        for (int i = 0; i < 3; i++) {
            cache.put(null, new HashMap<>());
        }

        assertTrue(cache.contains(null));
        assertTrue(cache.contains("1"));
        assertTrue(cache.contains("2"));
        assertTrue(cache.contains("3"));

        assertFalse(cache.contains("0"));

    }
}
