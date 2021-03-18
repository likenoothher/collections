package com.foxminded;

import gnu.trove.map.TCharLongMap;

import java.util.stream.IntStream;

public class CharCounterApp {

    public static void main(String[] args) {
        CharactersAmountCache charactersAmountCache = new CharactersAmountCache();
        UniqueCharsCounter charCounter = new UniqueCharsCounter(charactersAmountCache);

        printSymbolsAmount(charCounter.calculateCharactersNumber("122522"));
        printSymbolsAmount(charCounter.calculateCharactersNumber("1233"));
        printSymbolsAmount(charCounter.calculateCharactersNumber(""));
        printSymbolsAmount(charCounter.calculateCharactersNumber(null));


    }

    public static void printSymbolsAmount(TCharLongMap result) {
        if (result.isEmpty()) {
            System.out.println("There is no characters in the string");
            return;
        }
        printSortedResult(result);
    }

    private static void printSortedResult(TCharLongMap result) {
        IntStream.range(0, result.keys().length).
            mapToObj(i -> result.keys()[i]).
            sorted().
            forEach(x -> System.out.println("\"" + x + "\"" + " - " + result.get((char) x)));
    }
}
