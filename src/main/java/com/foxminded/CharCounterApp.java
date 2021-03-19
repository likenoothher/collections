package com.foxminded;

import java.util.Map;

public class CharCounterApp {

    public static void main(String[] args) {
        GenericCounterCache charactersAmountCache = new CharactersAmountCache();
        UniqueCharsCounter charCounter = new UniqueCharsCounter(charactersAmountCache);


        printSymbolsAmount(charCounter.calculateCharactersNumber("DDDtreyyy grh3j3 miymi; jjj"));
        printSymbolsAmount(charCounter.calculateCharactersNumber("dtreyyy grh3j3 miymi; jjj"));
        printSymbolsAmount(charCounter.calculateCharactersNumber(""));

    }

    public static void printSymbolsAmount(Map<Character, Long> result) {
        if (result.isEmpty()) {
            System.out.println("There is no characters in the string");
            return;
        }
        result.entrySet().stream().forEach(x -> System.out.println("\"" + x.getKey() + "\"" + " - " + x.getValue()));
    }

}
