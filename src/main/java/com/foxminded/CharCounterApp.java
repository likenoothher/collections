package com.foxminded;

import gnu.trove.iterator.TCharLongIterator;
import gnu.trove.map.TCharLongMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CharCounterApp {
    private static final InputCacheStorage INPUTS_CACHE_STORAGE = new InputCacheStorage();

    public static void main(String[] args) throws IOException {
        List<String> inputs = new ArrayList<>();
        inputs.add("Hello world!");
        inputs.add("Hello world!");
        inputs.add("21");
        printSymbolAmount(inputs);

    }

    public static void printSymbolAmount(List<String> inputs) throws IOException {
        TCharLongMap symbolAmountResult = countSymbols(inputs);
        for (TCharLongIterator it = symbolAmountResult.iterator(); it.hasNext(); ) {
            it.advance();
            System.out.println(String.format("\"%c\" - %d", it.key(), it.value()));

        }
    }

    private static TCharLongMap countSymbols(List<String> inputs) throws IOException {

        InputsCharCounter icc = new InputsCharCounter(INPUTS_CACHE_STORAGE);

        for (String input : inputs) {
            icc.addInputToCounter(input);
        }
        return icc.getSymbolAmountResult();
    }


}
