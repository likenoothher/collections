package com.foxminded;

import gnu.trove.map.TCharLongMap;
import gnu.trove.map.hash.TCharLongHashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class CharCounterTest {

    private Map<Character, Long> mapWithExpectedValues;
    private InputsCharCounter inputCharCounter;

    @Mock
    private InputCacheStorage cache;

    @Test
    public void whenGivenEmptyInput_thenResultMapIsEmpty() {
        inputCharCounter = new InputsCharCounter(cache);
        mapWithExpectedValues = new TreeMap();

        when(cache.isInputExist("")).thenReturn(false);

        inputCharCounter.addInputToCounter("");

        verify(cache).isInputExist("");

        assertEquals(mapWithExpectedValues, inputCharCounter.getSymbolAmountResult());

    }

    @Test
    public void whenCacheHasInput_thenCacheClassReturnExistedMap() {
        inputCharCounter = new InputsCharCounter(cache);
        mapWithExpectedValues = new TreeMap();

        mapWithExpectedValues.put('1', 2l);
        mapWithExpectedValues.put('2', 2l);
        mapWithExpectedValues.put('3', 4l);

        TCharLongMap mapOf1233Sequence = new TCharLongHashMap();
        mapOf1233Sequence.put('1', 1);
        mapOf1233Sequence.put('2', 1);
        mapOf1233Sequence.put('3', 2);

        when(cache.isInputExist("1233")).thenReturn(false).thenReturn(true);
        when(cache.getCachedInput("1233")).thenReturn(mapOf1233Sequence);
        inputCharCounter.addInputToCounter("1233");
        inputCharCounter.addInputToCounter("1233");

        verify(cache, times(2)).isInputExist("1233");
        verify(cache, times(1)).getCachedInput("1233");

        assertEquals(mapWithExpectedValues, inputCharCounter.getSymbolAmountResult());

    }

    @Test
    public void whenCacheHasEmptyInput_thenCacheClassReturnEmptyMap() {
        inputCharCounter = new InputsCharCounter(cache);
        mapWithExpectedValues = new TreeMap();

        TCharLongMap mapOfEmptyInput = new TCharLongHashMap();

        when(cache.isInputExist("")).thenReturn(false).thenReturn(true);
        when(cache.getCachedInput("")).thenReturn(mapOfEmptyInput);
        inputCharCounter.addInputToCounter("");
        inputCharCounter.addInputToCounter("");

        verify(cache, times(2)).isInputExist("");
        verify(cache, times(1)).getCachedInput("");

        assertEquals(mapWithExpectedValues, inputCharCounter.getSymbolAmountResult());

    }

    @Test
    public void whenCacheHasNotNotEmptyInput_thenCharCounterCalculateAmountOfSymbols() {
        inputCharCounter = new InputsCharCounter(cache);
        mapWithExpectedValues = new TreeMap();

        mapWithExpectedValues.put('1', 2l);
        mapWithExpectedValues.put('2', 2l);
        mapWithExpectedValues.put('3', 3l);
        mapWithExpectedValues.put('a', 1l);

        TCharLongMap mapOf1233Sequence = new TCharLongHashMap();
        mapOf1233Sequence.put('1', 1);
        mapOf1233Sequence.put('2', 1);
        mapOf1233Sequence.put('3', 2);

        TCharLongMap mapOf123aSequence = new TCharLongHashMap();
        mapOf123aSequence.put('1', 1);
        mapOf123aSequence.put('2', 1);
        mapOf123aSequence.put('3', 1);
        mapOf123aSequence.put('a', 1);

        when(cache.isInputExist("1233")).thenReturn(false);
        when(cache.isInputExist("123a")).thenReturn(false);

        inputCharCounter.addInputToCounter("1233");
        inputCharCounter.addInputToCounter("123a");

        verify(cache, times(1)).isInputExist("1233");
        verify(cache, times(1)).isInputExist("123a");

        assertEquals(mapWithExpectedValues, inputCharCounter.getSymbolAmountResult());

    }

    @Test
    public void whenGivenFewInputs_thenCountEntryOfEverySymbol() {
        inputCharCounter = new InputsCharCounter(cache);
        mapWithExpectedValues = new TreeMap();

        mapWithExpectedValues.put('1', 1l);
        mapWithExpectedValues.put('2', 1l);
        mapWithExpectedValues.put('3', 3l);
        mapWithExpectedValues.put('4', 2l);
        mapWithExpectedValues.put('5', 2l);
        mapWithExpectedValues.put('q', 3l);

        TCharLongMap mapOf345Sequence = new TCharLongHashMap();
        mapOf345Sequence.put('3', 1);
        mapOf345Sequence.put('4', 1);
        mapOf345Sequence.put('5', 1);

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

        assertEquals(mapWithExpectedValues, inputCharCounter.getSymbolAmountResult());

    }
}
