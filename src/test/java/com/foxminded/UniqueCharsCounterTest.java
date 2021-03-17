package com.foxminded;

import gnu.trove.map.TCharLongMap;
import gnu.trove.map.hash.TCharLongHashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UniqueCharsCounterTest {

    private TCharLongMap mapWithExpectedValues;
    private TCharLongMap mapWithActualValues;
    private UniqueCharsCounter inputCharCounter;

    @Mock
    private CharactersAmountCache cache;

    @Test
    public void whenGivenEmptyInput_thenResultMapIsEmpty() {
        inputCharCounter = new UniqueCharsCounter(cache);
        mapWithExpectedValues = new TCharLongHashMap();

        when(cache.contains("")).thenReturn(false);
        when(cache.putToCache("", mapWithExpectedValues)).thenReturn(mapWithExpectedValues);

        mapWithActualValues = inputCharCounter.calculateCharactersNumber("");

        verify(cache).contains("");
        verify(cache).putToCache("", mapWithExpectedValues);

        assertEquals(mapWithExpectedValues, mapWithActualValues);

    }

    @Test
    public void whenCacheHasInput_thenCacheClassReturnExistedMap() {
        inputCharCounter = new UniqueCharsCounter(cache);
        mapWithExpectedValues = new TCharLongHashMap();

        mapWithExpectedValues.put('1', 1);
        mapWithExpectedValues.put('2', 1);
        mapWithExpectedValues.put('3', 2);

        TCharLongMap mapOf1233Sequence = new TCharLongHashMap();
        mapOf1233Sequence.put('1', 1);
        mapOf1233Sequence.put('2', 1);
        mapOf1233Sequence.put('3', 2);

        when(cache.contains("1233")).thenReturn(true);
        when(cache.getCachedInput("1233")).thenReturn(mapOf1233Sequence);
        mapWithActualValues = inputCharCounter.calculateCharactersNumber("1233");

        verify(cache, times(1)).contains("1233");
        verify(cache, times(1)).getCachedInput("1233");

        assertEquals(mapWithExpectedValues, mapWithActualValues);

    }

    @Test
    public void whenCacheHasEmptyInput_thenCacheClassReturnExistedEmptyMap() {
        inputCharCounter = new UniqueCharsCounter(cache);
        mapWithExpectedValues = new TCharLongHashMap();

        when(cache.contains("")).thenReturn(true);
        when(cache.getCachedInput("")).thenReturn(mapWithExpectedValues);

        mapWithActualValues = inputCharCounter.calculateCharactersNumber("");

        verify(cache, times(1)).contains("");
        verify(cache, times(1)).getCachedInput("");

        assertEquals(mapWithExpectedValues, mapWithActualValues);

    }

    @Test
    public void whenCacheHasNotInput_thenCharCounterCalculateSymbolsNumberAndReturnMap() {
        inputCharCounter = new UniqueCharsCounter(cache);
        mapWithExpectedValues = new TCharLongHashMap();

        mapWithExpectedValues.put('1', 1);
        mapWithExpectedValues.put('2', 1);
        mapWithExpectedValues.put('3', 2);

        when(cache.contains("1233")).thenReturn(false);
        when(cache.putToCache("1233", mapWithExpectedValues)).thenReturn(mapWithExpectedValues);

        mapWithActualValues = inputCharCounter.calculateCharactersNumber("1233");

        assertEquals(mapWithExpectedValues, mapWithActualValues);

        verify(cache, times(1)).contains("1233");
        verify(cache, times(1)).putToCache("1233", mapWithExpectedValues);

    }

    @Test
    public void whenCharCounterIsUsedFewTimes_thenReturnSeparateSymbolsNumberMapPerEachUsing() {
        inputCharCounter = new UniqueCharsCounter(cache);
        mapWithExpectedValues = new TCharLongHashMap();

        TCharLongMap mapOf123Sequence = new TCharLongHashMap();
        mapOf123Sequence.put('1', 1);
        mapOf123Sequence.put('2', 1);
        mapOf123Sequence.put('3', 1);

        TCharLongMap mapOf345Sequence = new TCharLongHashMap();
        mapOf345Sequence.put('3', 1);
        mapOf345Sequence.put('4', 1);
        mapOf345Sequence.put('5', 1);

        when(cache.contains("123")).thenReturn(false);
        when(cache.putToCache("123", mapOf123Sequence)).thenReturn(mapOf123Sequence);

        when(cache.contains("345")).thenReturn(false).thenReturn(true);
        when(cache.putToCache("345", mapOf345Sequence)).thenReturn(mapOf345Sequence);
        when(cache.getCachedInput("345")).thenReturn(mapOf345Sequence);


        mapWithActualValues = inputCharCounter.calculateCharactersNumber("123");
        assertEquals(mapOf123Sequence, mapWithActualValues);

        mapWithActualValues = inputCharCounter.calculateCharactersNumber("345");
        assertEquals(mapOf345Sequence, mapWithActualValues);

        mapWithActualValues = inputCharCounter.calculateCharactersNumber("345");
        assertEquals(mapOf345Sequence, mapWithActualValues);

        verify(cache, times(1)).contains("123");
        verify(cache, times(1)).putToCache("123", mapOf123Sequence);
        verify(cache, times(2)).contains("345");
        verify(cache, times(1)).putToCache("345", mapOf345Sequence);
        verify(cache, times(1)).getCachedInput("345");

    }
}
