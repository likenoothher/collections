package com.foxminded.charcounter.cache;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;
import static org.junit.Assume.assumeFalse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class CharactersAmountCacheTest {

    @Test
    public void whenCacheContainsString_thenReturnTrue() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache<>(2);
        Map<Character, Long> mapOf11123Sequence = new HashMap<>();
        mapOf11123Sequence.put('1', 3L);
        mapOf11123Sequence.put('2', 1L);
        mapOf11123Sequence.put('3', 1L);
        cache.put("11123", mapOf11123Sequence);

        assumeTrue(cache.contains("11123"));
    }

    @Test
    public void whenCacheDoesNotContainsString_thenReturnFalse() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache<>(2);
        Map<Character, Long> mapOf11123Sequence = new HashMap<>();
        mapOf11123Sequence.put('1', 3L);
        mapOf11123Sequence.put('2', 1L);
        mapOf11123Sequence.put('3', 1L);
        cache.put("11123", mapOf11123Sequence);

        assumeFalse(cache.contains("23"));
    }

    @Test
    public void whenGettingKeyEqualsExistedCache_thenReturnSymbolsNumberMap() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache<>(2);
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
    public void whenGettingKeyIsNotEqualsAnyExistedCache_thenReturnNull() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache<>(2);

        assertNull(cache.get("not_existed_key"));
    }

    @Test
    public void whenPutNewSymbolsNumberMapToCache_thenSaveMapAndReturnIt() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache<>(2);
        Map<Character, Long> mapOf11123Sequence = new HashMap<>();
        mapOf11123Sequence.put('1', 3L);
        mapOf11123Sequence.put('2', 1L);
        mapOf11123Sequence.put('3', 1L);
        cache.put("11123", mapOf11123Sequence);

        Map<Character, Long> expectedMap = new HashMap<>();
        expectedMap.put('1', 3L);
        expectedMap.put('2', 1L);
        expectedMap.put('3', 1L);

        assumeTrue(cache.contains("11123"));
        assertEquals(expectedMap, cache.get("11123"));
    }

    @Test
    public void whenPutExistedKeyValuePair_thenWillNotSaveMapUnderThisKeyReturnCurrentKeysValue() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache<>(2);
        Map<Character, Long> mapOf11123Sequence = new HashMap<>();
        mapOf11123Sequence.put('1', 3L);
        mapOf11123Sequence.put('2', 1L);
        mapOf11123Sequence.put('3', 1L);

        Map<Character, Long> mapOf432Sequence = new HashMap<>();
        mapOf432Sequence.put('4', 1L);
        mapOf432Sequence.put('3', 1L);
        mapOf432Sequence.put('2', 1L);

        cache.put("11123", mapOf11123Sequence);
        cache.put("11123", mapOf11123Sequence);
        assertEquals(mapOf432Sequence, cache.put("11123", mapOf432Sequence));

    }


    @Test
    public void whenPutSymbolsNumberMapOverCapacity_thenRemoveRandomMapWithMinNumberOfUsing() {
        GenericCounterCache<String, Map<Character, Long>> cache = new CharactersAmountCache<>(2);
        Map<Character, Long> mapOf11123Sequence = new HashMap<>();

        Map<Character, Long> mapOf432Sequence = new HashMap<>();

        Map<Character, Long> mapOf888Sequence = new HashMap<>();

        cache.put("888", mapOf888Sequence);
        cache.put("11123", mapOf11123Sequence);
        cache.get("888");
        cache.put("432", mapOf432Sequence);

        assertTrue(cache.contains("432"));
        assertTrue(cache.contains("888"));
        assertFalse(cache.contains("11123"));

    }

}