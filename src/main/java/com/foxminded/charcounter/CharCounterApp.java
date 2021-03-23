package com.foxminded.charcounter;

import com.foxminded.charcounter.cache.CharactersAmountCache;
import com.foxminded.charcounter.cache.GenericCounterCache;
import com.foxminded.charcounter.counter.UniqueCharsCounter;
import com.foxminded.charcounter.output.ConsoleFormatter;
import com.foxminded.charcounter.output.OutputFormatter;


public class CharCounterApp {

    public static void main(String[] args) {
        GenericCounterCache charactersAmountCache = new CharactersAmountCache(10);
        UniqueCharsCounter charCounter = new UniqueCharsCounter(charactersAmountCache);
        OutputFormatter<Character,Long> consoleFormatter = new ConsoleFormatter<>();


        for (int i = 0; i < 10; i++) {
            consoleFormatter.processResult(charCounter.calculateCharactersNumber(i +""));
        }
        for (int i = 2; i < 16; i++) {
            consoleFormatter.processResult(charCounter.calculateCharactersNumber(i +""));
        }
        for (int i = 2; i < 16; i++) {
            consoleFormatter.processResult(charCounter.calculateCharactersNumber(122 +""));
        }
    }

}
