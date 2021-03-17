package com.foxminded;

import gnu.trove.TCollections;
import gnu.trove.map.TCharLongMap;
import gnu.trove.map.hash.TCharLongHashMap;


public class UniqueCharsCounter {
    private CharactersAmountCache charactersAmountCache;

    public UniqueCharsCounter(CharactersAmountCache charactersAmountCache) {
        this.charactersAmountCache = charactersAmountCache;
    }

    public TCharLongMap calculateCharactersNumber(String input) {
        if (charactersAmountCache.contains(input)) {
            return charactersAmountCache.getCachedInput(input);
        }
        TCharLongMap uniqueCharactersNumber = TCollections.unmodifiableMap(calculateNewInput(input));
        return charactersAmountCache.putToCache(input, uniqueCharactersNumber);
    }

    private TCharLongMap calculateNewInput(String input) {
        TCharLongMap uniqueCharactersNumber = new TCharLongHashMap();
        if (input.equals("")) {
            return uniqueCharactersNumber;
        }
        fillCharactersNumberMap(uniqueCharactersNumber, input);

        return uniqueCharactersNumber;
    }

    private void fillCharactersNumberMap(TCharLongMap uniqueCharactersNumber, String input) {
        char[] charArray = input.toCharArray();
        for (char symbol : charArray) {
            if (uniqueCharactersNumber.containsKey(symbol)) {
                uniqueCharactersNumber.put(symbol, uniqueCharactersNumber.get(symbol) + 1);
            } else {
                uniqueCharactersNumber.put(symbol, 1);
            }
        }
    }
}
