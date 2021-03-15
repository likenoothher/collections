package com.foxminded;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class InputsCharCounter {
    private Map<String, Integer> amountOfSymbols = new TreeMap<String, Integer>();
    private InputsCacheStorage intputsStorage;

    public InputsCharCounter(InputsCacheStorage intputsStorage) {
        this.intputsStorage = intputsStorage;
    }

    public void addInputToCounter(String initialInput) {
        Map<String, Integer> amountSymbolMap = getSymbolAmountMap(initialInput);

        for (Map.Entry<String, Integer> entry : amountSymbolMap.entrySet()) {
            if (amountOfSymbols.containsKey(entry.getKey())) {
                amountOfSymbols.put(entry.getKey(), amountOfSymbols.get(entry.getKey()) + entry.getValue());
            } else {
                amountOfSymbols.put(entry.getKey(), entry.getValue());
            }
        }
    }

    private Map<String, Integer> getSymbolAmountMap(String initialInput) {
        Map<String, Integer> symbolAmountMap;
        if (intputsStorage.isInputExist(initialInput)) {
            symbolAmountMap = intputsStorage.getCachedInput(initialInput);
        } else {
            symbolAmountMap = createSymbolAmountMap(initialInput);
            intputsStorage.addToCache(initialInput, symbolAmountMap);
        }
        return symbolAmountMap;
    }

    private Map<String, Integer> createSymbolAmountMap(String initialInput) {
        Map<String, Integer> symbolAmountMap = new HashMap<String, Integer>();
        String[] charArray = initialInput.split("");
        for (String symbol : charArray) {
            if (symbolAmountMap.containsKey(symbol)) {
                symbolAmountMap.put(symbol, symbolAmountMap.get(symbol) + 1);
            } else {
                symbolAmountMap.put(symbol, 1);
            }
        }
        return symbolAmountMap;
    }

    public Map<String, Integer> getAmountOfSymbols() {
        return amountOfSymbols;
    }
}
