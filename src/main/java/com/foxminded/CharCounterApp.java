package com.foxminded;

import java.io.IOException;
import java.util.Map;

public class CharCounterApp {
    private static final InputCacheStorage INPUTS_CACHE_STORAGE = new InputCacheStorage();

    public static void main(String[] args) throws IOException {
        printSymbolAmount("edcbaa");
        printSymbolAmount("");
        printSymbolAmount("Hello world!");
        printSymbolAmount("");

    }

    public static void printSymbolAmount(String input) throws IOException {
        Map<Character, Long> symbolAmountResult = countSymbols(input);
        if (symbolAmountResult.isEmpty()) {
            System.out.println("There is no characters in the string");
        } else {
            for (Map.Entry<Character, Long> entry : symbolAmountResult.entrySet()) {
                System.out.println(String.format("\"%c\" - %d", entry.getKey(), entry.getValue()));
            }
        }
    }

    private static Map<Character, Long> countSymbols(String input) throws IOException {

        InputsCharCounter icc = new InputsCharCounter(INPUTS_CACHE_STORAGE);

        icc.addInputToCounter(input);

        return icc.getSymbolAmountResult();
    }
}
