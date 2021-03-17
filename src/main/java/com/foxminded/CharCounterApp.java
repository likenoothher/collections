package com.foxminded;


import gnu.trove.map.TCharLongMap;

import java.util.stream.IntStream;

public class CharCounterApp {

    public static void main(String[] args) {
        CharactersAmountCache charactersAmountCache = new CharactersAmountCache();
        UniqueCharsCounter charCounter = new UniqueCharsCounter(charactersAmountCache);

        printSymbolsAmount(charCounter.calculateCharactersNumber("1233"));
        printSymbolsAmount(charCounter.calculateCharactersNumber(""));

        printSymbolsAmount(charCounter.calculateCharactersNumber("122522"));


    }

    public static void printSymbolsAmount(TCharLongMap result) {
        if (result.isEmpty()) {
            System.out.println("There is no characters in the string");
            System.out.println(result.getClass().getSimpleName());
            return;
        }
        printSortedResult(result);


    }

    private static void printSortedResult(TCharLongMap result) {
        char[] keysOfResult = result.keys();
        IntStream.range(0, keysOfResult.length).
            mapToObj(i -> keysOfResult[i]).
            sorted().
            forEach(x -> System.out.println("\"" + x + "\"" + " - " + result.get((char) x)));
    }

}
