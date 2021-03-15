package com.foxminded;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class CharCounterTest {

    private Map<String, Integer> mapWithExpectedValues;

    @Mock
    private InputsCacheStorage cache;

    @Test
    public void whenGivenEmptyInput_thenResultIsEmptyAmountOne() {
        InputsCharCounter inputCharCounter = new InputsCharCounter(cache);
        mapWithExpectedValues = new HashMap<>();
        mapWithExpectedValues.put("", 1);

        when(cache.isInputExist("")).thenReturn(false);

        inputCharCounter.addInputToCounter("");

        verify(cache).isInputExist("");

        assertEquals(mapWithExpectedValues, inputCharCounter.getAmountOfSymbols());

    }

    @Test
    public void whenCacheHasInput_thenCacheClassReturnExistedString() {

        InputsCharCounter inputCharCounter = new InputsCharCounter(cache);

        mapWithExpectedValues = new HashMap<>();
        mapWithExpectedValues.put("1", 2);
        mapWithExpectedValues.put("2", 2);
        mapWithExpectedValues.put("3", 4);

        Map<String, Integer> mapOf1233Sequence = new HashMap<>();
        mapOf1233Sequence.put("1", 1);
        mapOf1233Sequence.put("2", 1);
        mapOf1233Sequence.put("3", 2);

        when(cache.isInputExist("1233")).thenReturn(false).thenReturn(true);
        when(cache.getCachedInput("1233")).thenReturn(mapOf1233Sequence);
        inputCharCounter.addInputToCounter("1233");
        inputCharCounter.addInputToCounter("1233");

        verify(cache, times(2)).isInputExist("1233");
        verify(cache, times(1)).getCachedInput("1233");

        assertEquals(mapWithExpectedValues, inputCharCounter.getAmountOfSymbols());

    }

    @Test
    public void whenGivenFewInputs_thenCountEntryOfEverySymbol() {
        InputsCharCounter inputCharCounter = new InputsCharCounter(cache);
        mapWithExpectedValues = new HashMap<>();
        mapWithExpectedValues.put("1", 1);
        mapWithExpectedValues.put("2", 1);
        mapWithExpectedValues.put("3", 3);
        mapWithExpectedValues.put("4", 2);
        mapWithExpectedValues.put("5", 2);
        mapWithExpectedValues.put("q", 3);

        Map<String, Integer> mapOf345Sequence = new HashMap<>();
        mapOf345Sequence.put("3", 1);
        mapOf345Sequence.put("4", 1);
        mapOf345Sequence.put("5", 1);

        when(cache.isInputExist("123")).thenReturn(false);
        when(cache.isInputExist("345")).thenReturn(false).thenReturn(true);
        when(cache.getCachedInput("345")).thenReturn(mapOf345Sequence);
        when(cache.isInputExist("qqq")).thenReturn(false);

        inputCharCounter.addInputToCounter("123");
        inputCharCounter.addInputToCounter("345");
        inputCharCounter.addInputToCounter("qqq");
        inputCharCounter.addInputToCounter("345");

        verify(cache, times(1)).isInputExist("123");
        verify(cache, times(2)).isInputExist("345");
        verify(cache, times(1)).isInputExist("qqq");
        verify(cache, times(1)).getCachedInput("345");

        assertEquals(mapWithExpectedValues, inputCharCounter.getAmountOfSymbols());

    }
}
