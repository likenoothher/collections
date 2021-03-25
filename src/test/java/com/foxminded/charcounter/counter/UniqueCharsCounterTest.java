package com.foxminded.charcounter.counter;

import com.foxminded.charcounter.cache.CharactersAmountCacheStub;
import com.foxminded.charcounter.cache.GenericCounterCache;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class UniqueCharsCounterTest {

    private Map<Character, Long> mapWithExpectedValues;
    private Map<Character, Long> mapWithActualValues;
    private UniqueCharsCounter inputCharCounter;
    private GenericCounterCache<String, Map<Character, Long>> cacheStub = new CharactersAmountCacheStub<>();


    @Test
    public void whenNullInput_throwIAException() {
        inputCharCounter = new UniqueCharsCounter(cacheStub);
        assertThrows(IllegalArgumentException.class, () -> inputCharCounter.calculateCharactersNumber(null));
    }

    @Test
    public void whenGivenEmptyInput_thenResultMapIsEmpty() {
        inputCharCounter = new UniqueCharsCounter(cacheStub);
        mapWithExpectedValues = new TreeMap<>();

        mapWithActualValues = inputCharCounter.calculateCharactersNumber("");

        assertEquals(mapWithExpectedValues, mapWithActualValues);

    }

    @Test
    public void whenGivenNotEmptyString_thenCharCounterCalculateSymbolsNumberAndReturnMap() {
        inputCharCounter = new UniqueCharsCounter(cacheStub);
        mapWithExpectedValues = new TreeMap<>();

        mapWithExpectedValues.put('1', 1L);
        mapWithExpectedValues.put('2', 1L);
        mapWithExpectedValues.put('3', 2L);

        mapWithActualValues = inputCharCounter.calculateCharactersNumber("1233");

        assertEquals(mapWithExpectedValues, mapWithActualValues);

    }

    @Test
    public void whenCharCounterIsUsedFewTimes_thenReturnSeparateSymbolsNumberMapPerEachUsing() {
        inputCharCounter = new UniqueCharsCounter(cacheStub);

        Map<Character, Long> mapOf123Sequence = new TreeMap<>();
        mapOf123Sequence.put('1', 1L);
        mapOf123Sequence.put('2', 1L);
        mapOf123Sequence.put('3', 1L);

        Map<Character, Long> mapOf345Sequence = new TreeMap<>();
        mapOf345Sequence.put('3', 1L);
        mapOf345Sequence.put('4', 1L);
        mapOf345Sequence.put('5', 1L);

        mapWithActualValues = inputCharCounter.calculateCharactersNumber("123");
        assertEquals(mapOf123Sequence, mapWithActualValues);

        mapWithActualValues = inputCharCounter.calculateCharactersNumber("345");
        assertEquals(mapOf345Sequence, mapWithActualValues);

    }

    @Test
    public void whenGivenFewWordsString_thenCountUniqueSymbolsNumberOfAllWords() {
        inputCharCounter = new UniqueCharsCounter(cacheStub);
        mapWithExpectedValues = new TreeMap<>();
        mapWithExpectedValues.put(' ', 3L);
        mapWithExpectedValues.put('3', 2L);
        mapWithExpectedValues.put(';', 1L);
        mapWithExpectedValues.put('d', 1L);
        mapWithExpectedValues.put('e', 1L);
        mapWithExpectedValues.put('g', 1L);
        mapWithExpectedValues.put('h', 1L);
        mapWithExpectedValues.put('i', 2L);
        mapWithExpectedValues.put('j', 4L);
        mapWithExpectedValues.put('m', 2L);
        mapWithExpectedValues.put('r', 2L);
        mapWithExpectedValues.put('y', 4L);
        mapWithExpectedValues.put('t', 1L);

        mapWithActualValues = inputCharCounter.calculateCharactersNumber("dtreyyy grh3j3 miymi; jjj");

        assertEquals(mapWithExpectedValues, mapWithActualValues);

    }

    @Test
    public void whenGivenStringWithUpperCaseSymbols_thenCountSeparatelyUpperAndLowerCaseSymbols() {
        inputCharCounter = new UniqueCharsCounter(cacheStub);
        mapWithExpectedValues = new TreeMap<>();
        mapWithExpectedValues.put(' ', 3L);
        mapWithExpectedValues.put('3', 2L);
        mapWithExpectedValues.put(';', 1L);
        mapWithExpectedValues.put('d', 1L);
        mapWithExpectedValues.put('e', 1L);
        mapWithExpectedValues.put('g', 1L);
        mapWithExpectedValues.put('h', 1L);
        mapWithExpectedValues.put('i', 2L);
        mapWithExpectedValues.put('j', 4L);
        mapWithExpectedValues.put('m', 1L);
        mapWithExpectedValues.put('r', 2L);
        mapWithExpectedValues.put('y', 2L);
        mapWithExpectedValues.put('t', 1L);
        mapWithExpectedValues.put('Y', 2L);
        mapWithExpectedValues.put('M', 1L);

        mapWithActualValues = inputCharCounter.calculateCharactersNumber("dtreyYY grh3j3 Miymi; jjj");

        assertEquals(mapWithExpectedValues, mapWithActualValues);

    }
}
