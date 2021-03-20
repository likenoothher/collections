package com.foxminded.charcounter.counter;

import com.foxminded.charcounter.cache.GenericCounterCache;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;


public class UniqueCharsCounter {
    private GenericCounterCache<String, Map<Character, Long>> charactersAmountCache;

    public UniqueCharsCounter(GenericCounterCache charactersAmountCache) {
        this.charactersAmountCache = charactersAmountCache;
    }

    public Map<Character, Long> calculateCharactersNumber(String input) {
        checkInput(input);

        if (charactersAmountCache.contains(input)) {
            return charactersAmountCache.get(input);
        }
        Map<Character, Long> uniqueCharactersNumber = calculateNewCharactersNumber(input);
        return charactersAmountCache.put(input, Collections.unmodifiableMap(uniqueCharactersNumber));
    }

    private Map<Character, Long> calculateNewCharactersNumber(String input) {
        Map<Character, Long> uniqueCharactersNumber = new TreeMap<>();
        if (input.equals("")) {
            return uniqueCharactersNumber;
        }
        fillCharactersNumberMap(uniqueCharactersNumber, input);
        return uniqueCharactersNumber;
    }

    private void fillCharactersNumberMap(Map<Character, Long> uniqueCharactersNumber, String input) {

        char[] charArray = input.toCharArray();
        for (char symbol : charArray) {
            if (uniqueCharactersNumber.containsKey(symbol)) {
                uniqueCharactersNumber.put(symbol, uniqueCharactersNumber.get(symbol) + 1);
            } else {
                uniqueCharactersNumber.put(symbol, 1L);
            }
        }
    }

    private void checkInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
    }
}
