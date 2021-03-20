package com.foxminded.charcounter;

import com.foxminded.charcounter.cache.CharactersAmountCache;
import com.foxminded.charcounter.cache.GenericCounterCache;
import com.foxminded.charcounter.counter.UniqueCharsCounter;
import com.foxminded.charcounter.output.OutputFormatter;

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
        OutputFormatter<Character, Long> formatter = new OutputFormatter<Character, Long>() {
            @Override
            public boolean isEmpty(Map<Character, Long> result) {
                return result.isEmpty();
            }

            @Override
            public void processResult(Map<Character, Long> result) {
                result.forEach((key, value) -> System.out.println("\"" + key + "\"" + " - " + value));
            }
        };

        if (formatter.isEmpty(result)) {
            System.out.println("There is no characters in the string");
        }

        formatter.processResult(result);
    }

}
