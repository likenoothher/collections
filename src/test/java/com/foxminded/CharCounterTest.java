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

    @Mock
    private CacheInputsStorage cache;

    @Test
    public void whenAddToResultExistedString_thenCacheClassReturnExistedString() {

        InputCharCounter inputCharCounter = new InputCharCounter(cache);

        Map<String, Integer> mapWithExpectedValues = new HashMap<>();
        mapWithExpectedValues.put("1", 2);
        mapWithExpectedValues.put("2", 2);
        mapWithExpectedValues.put("3", 4);

        Map<String, Integer> mapOf1233Sequence = new HashMap<>();
        mapOf1233Sequence.put("1", 1);
        mapOf1233Sequence.put("2", 1);
        mapOf1233Sequence.put("3", 2);

        when(cache.isInputExist("1233")).thenReturn(true);
        when(cache.getValues("1233")).thenReturn(mapOf1233Sequence);
        inputCharCounter.addInputResultMap("1233");
        inputCharCounter.addInputResultMap("1233");
        verify(cache, times(2)).getValues("1233");
        verify(cache, times(2)).isInputExist("1233");

        assertEquals(mapWithExpectedValues, inputCharCounter.getResult());

    }

    @Test
    public void whenGivenFewInputs_thenCountEntryOfEverySymbol() {
        InputCharCounter inputCharCounter = new InputCharCounter(cache);
        Map<String, Integer> mapWithExpectedValues = new HashMap<>();
        mapWithExpectedValues.put("1", 1);
        mapWithExpectedValues.put("2", 1);
        mapWithExpectedValues.put("3", 3);
        mapWithExpectedValues.put("4", 2);
        mapWithExpectedValues.put("5", 2);
        mapWithExpectedValues.put("q", 3);

        inputCharCounter.addInputResultMap("123");
        inputCharCounter.addInputResultMap("345");
        inputCharCounter.addInputResultMap("345");
        inputCharCounter.addInputResultMap("qqq");

        assertEquals(mapWithExpectedValues, inputCharCounter.getResult());

    }
}
