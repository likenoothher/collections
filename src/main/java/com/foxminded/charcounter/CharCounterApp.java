package com.foxminded.charcounter;

import com.foxminded.charcounter.cache.CharactersAmountCache;
import com.foxminded.charcounter.cache.GenericCounterCache;
import com.foxminded.charcounter.counter.UniqueCharsCounter;
import com.foxminded.charcounter.output.ConsoleWriter;
import com.foxminded.charcounter.output.OutputWriter;

import java.util.Map;


public class CharCounterApp {

    public static void main(String[] args) {
        GenericCounterCache<String, Map<Character, Long>> charactersAmountCache = new CharactersAmountCache(5);
        UniqueCharsCounter charCounter = new UniqueCharsCounter(charactersAmountCache);
        OutputWriter<Character, Long> consoleWriter = new ConsoleWriter<>();


        for (int i = 0; i < 5; i++) {
            consoleWriter.printResult(charCounter.calculateCharactersNumber(i + ""));
        }
        for (int i = 2; i < 8; i++) {
            consoleWriter.printResult(charCounter.calculateCharactersNumber(i + ""));
        }

    }

}
