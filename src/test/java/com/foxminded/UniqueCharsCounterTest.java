package com.foxminded;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UniqueCharsCounterTest {

    private Map<Character, Long> mapWithExpectedValues;
    private Map<Character, Long> mapWithActualValues;
    private UniqueCharsCounter inputCharCounter;
    private GenericCounterCache<String, Map<Character, Long>> cacheStub = new CharactersAmountCacheStub<>();

    @Mock
    private GenericCounterCache<String, Map<Character, Long>> cache;

    @Test
    public void whenNullInput_throwIAException() {
        inputCharCounter = new UniqueCharsCounter(cache);
        assertThrows(IllegalArgumentException.class, () -> inputCharCounter.calculateCharactersNumber(null));
    }

    @Test
    public void whenGivenEmptyInput_thenResultMapIsEmpty() {
        inputCharCounter = new UniqueCharsCounter(cache);
        mapWithExpectedValues = new TreeMap<>();

        when(cache.contains("")).thenReturn(false);
        when(cache.put("", mapWithExpectedValues)).thenReturn(mapWithExpectedValues);

        mapWithActualValues = inputCharCounter.calculateCharactersNumber("");

        verify(cache).contains("");
        verify(cache).put("", mapWithExpectedValues);

        assertEquals(mapWithExpectedValues, mapWithActualValues);

    }

    @Test
    public void whenCacheHasInput_thenCacheClassReturnExistedMap() {
        inputCharCounter = new UniqueCharsCounter(cache);
        mapWithExpectedValues = new TreeMap<>();

        mapWithExpectedValues.put('1', 1l);
        mapWithExpectedValues.put('2', 1l);
        mapWithExpectedValues.put('3', 2l);

        Map<Character, Long> mapOf1233Sequence = new TreeMap<>();
        mapOf1233Sequence.put('1', 1l);
        mapOf1233Sequence.put('2', 1l);
        mapOf1233Sequence.put('3', 2l);

        when(cache.contains("1233")).thenReturn(true);
        when(cache.get("1233")).thenReturn(mapOf1233Sequence);
        mapWithActualValues = inputCharCounter.calculateCharactersNumber("1233");

        verify(cache, times(1)).contains("1233");
        verify(cache, times(1)).get("1233");

        assertEquals(mapWithExpectedValues, mapWithActualValues);

    }

    @Test
    public void whenCacheHasEmptyInput_thenCacheClassReturnExistedEmptyMap() {
        inputCharCounter = new UniqueCharsCounter(cache);
        mapWithExpectedValues = new TreeMap<>();

        when(cache.contains("")).thenReturn(true);
        when(cache.get("")).thenReturn(mapWithExpectedValues);

        mapWithActualValues = inputCharCounter.calculateCharactersNumber("");

        verify(cache, times(1)).contains("");
        verify(cache, times(1)).get("");

        assertEquals(mapWithExpectedValues, mapWithActualValues);

    }

    @Test
    public void whenCacheHasNotInput_thenCharCounterCalculateSymbolsNumberAndReturnMap() {
        inputCharCounter = new UniqueCharsCounter(cache);
        mapWithExpectedValues = new TreeMap<>();

        mapWithExpectedValues.put('1', 1l);
        mapWithExpectedValues.put('2', 1l);
        mapWithExpectedValues.put('3', 2l);

        when(cache.contains("1233")).thenReturn(false);
        when(cache.put("1233", mapWithExpectedValues)).thenReturn(mapWithExpectedValues);

        mapWithActualValues = inputCharCounter.calculateCharactersNumber("1233");

        assertEquals(mapWithExpectedValues, mapWithActualValues);

        verify(cache, times(1)).contains("1233");
        verify(cache, times(1)).put("1233", mapWithExpectedValues);

    }

    @Test
    public void whenCharCounterIsUsedFewTimes_thenReturnSeparateSymbolsNumberMapPerEachUsing() {
        inputCharCounter = new UniqueCharsCounter(cache);

        Map<Character, Long> mapOf123Sequence = new TreeMap<>();
        mapOf123Sequence.put('1', 1l);
        mapOf123Sequence.put('2', 1l);
        mapOf123Sequence.put('3', 1l);

        Map<Character, Long> mapOf345Sequence = new TreeMap<>();
        mapOf345Sequence.put('3', 1l);
        mapOf345Sequence.put('4', 1l);
        mapOf345Sequence.put('5', 1l);

        when(cache.contains("123")).thenReturn(false);
        when(cache.put("123", mapOf123Sequence)).thenReturn(mapOf123Sequence);

        when(cache.contains("345")).thenReturn(false).thenReturn(true);
        when(cache.put("345", mapOf345Sequence)).thenReturn(mapOf345Sequence);
        when(cache.get("345")).thenReturn(mapOf345Sequence);


        mapWithActualValues = inputCharCounter.calculateCharactersNumber("123");
        assertEquals(mapOf123Sequence, mapWithActualValues);

        mapWithActualValues = inputCharCounter.calculateCharactersNumber("345");
        assertEquals(mapOf345Sequence, mapWithActualValues);

        mapWithActualValues = inputCharCounter.calculateCharactersNumber("345");
        assertEquals(mapOf345Sequence, mapWithActualValues);

        verify(cache, times(1)).contains("123");
        verify(cache, times(1)).put("123", mapOf123Sequence);
        verify(cache, times(2)).contains("345");
        verify(cache, times(1)).put("345", mapOf345Sequence);
        verify(cache, times(1)).get("345");

    }

    @Test
    public void whenGivenFewWordsString_thenCountUniqueSymbolsNumberInAllWords() {
        inputCharCounter = new UniqueCharsCounter(cache);
        mapWithExpectedValues = new TreeMap<>();
        mapWithExpectedValues.put(' ', 3l);
        mapWithExpectedValues.put('3', 2l);
        mapWithExpectedValues.put(';', 1l);
        mapWithExpectedValues.put('d', 1l);
        mapWithExpectedValues.put('e', 1l);
        mapWithExpectedValues.put('g', 1l);
        mapWithExpectedValues.put('h', 1l);
        mapWithExpectedValues.put('i', 2l);
        mapWithExpectedValues.put('j', 4l);
        mapWithExpectedValues.put('m', 2l);
        mapWithExpectedValues.put('r', 2l);
        mapWithExpectedValues.put('y', 4l);
        mapWithExpectedValues.put('t', 1l);

        when(cache.contains("dtreyyy grh3j3 miymi; jjj")).thenReturn(false);
        when(cache.put("dtreyyy grh3j3 miymi; jjj", mapWithExpectedValues)).thenReturn(mapWithExpectedValues);

        mapWithActualValues = inputCharCounter.calculateCharactersNumber("dtreyyy grh3j3 miymi; jjj");

        assertEquals(mapWithExpectedValues, mapWithActualValues);

        verify(cache, times(1)).contains("dtreyyy grh3j3 miymi; jjj");
        verify(cache, times(1)).put("dtreyyy grh3j3 miymi; jjj", mapWithExpectedValues);
    }

    @Test
    public void whenGivenStringWithUpperCaseSymbols_thenCountSeparatelyUpperAndLowerCaseSymbols() {
        inputCharCounter = new UniqueCharsCounter(cache);
        mapWithExpectedValues = new TreeMap<>();
        mapWithExpectedValues.put(' ', 3l);
        mapWithExpectedValues.put('3', 2l);
        mapWithExpectedValues.put(';', 1l);
        mapWithExpectedValues.put('d', 1l);
        mapWithExpectedValues.put('e', 1l);
        mapWithExpectedValues.put('g', 1l);
        mapWithExpectedValues.put('h', 1l);
        mapWithExpectedValues.put('i', 2l);
        mapWithExpectedValues.put('j', 4l);
        mapWithExpectedValues.put('m', 1l);
        mapWithExpectedValues.put('r', 2l);
        mapWithExpectedValues.put('y', 2l);
        mapWithExpectedValues.put('t', 1l);
        mapWithExpectedValues.put('Y', 2l);
        mapWithExpectedValues.put('M', 1l);

        when(cache.contains("dtreyYY grh3j3 Miymi; jjj")).thenReturn(false);
        when(cache.put("dtreyYY grh3j3 Miymi; jjj", mapWithExpectedValues)).thenReturn(mapWithExpectedValues);

        mapWithActualValues = inputCharCounter.calculateCharactersNumber("dtreyYY grh3j3 Miymi; jjj");

        assertEquals(mapWithExpectedValues, mapWithActualValues);

        verify(cache, times(1)).contains("dtreyYY grh3j3 Miymi; jjj");
        verify(cache, times(1)).put("dtreyYY grh3j3 Miymi; jjj", mapWithExpectedValues);
    }
}
