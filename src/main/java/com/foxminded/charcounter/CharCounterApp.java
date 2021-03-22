package com.foxminded.charcounter;

import com.foxminded.charcounter.cache.CharactersAmountCache;
import com.foxminded.charcounter.cache.GenericCounterCache;
import com.foxminded.charcounter.counter.UniqueCharsCounter;
import com.foxminded.charcounter.output.OutputFormatter;

import java.util.Map;

public class CharCounterApp {

    public static void main(String[] args) {
        GenericCounterCache charactersAmountCache = new CharactersAmountCache(10);
        UniqueCharsCounter charCounter = new UniqueCharsCounter(charactersAmountCache);


        for (int i = 0; i < 10; i++) {
            printSymbolsAmount(charCounter.calculateCharactersNumber(i +""));
        }
        for (int i = 2; i < 16; i++) {
            printSymbolsAmount(charCounter.calculateCharactersNumber(i +""));
        }
        for (int i = 2; i < 16; i++) {
            printSymbolsAmount(charCounter.calculateCharactersNumber(122 +""));
        }
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
                System.out.println("--------------------------------------------------------");
            }
        };

        if (formatter.isEmpty(result)) {
            System.out.println("There is no characters in the string");
            return;
        }

        formatter.processResult(result);
    }

}
