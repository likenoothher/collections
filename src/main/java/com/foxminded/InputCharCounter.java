package com.foxminded;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class InputCharCounter {
    private Map<String, Integer> amountOfValues = new TreeMap<String, Integer>();
    private CacheInputsStorage intputsStorage;

    public InputCharCounter(CacheInputsStorage intputsStorage) {
        this.intputsStorage = intputsStorage;
    }

    public void addInputResultMap(String initialInput) {
        Map<String, Integer> amountSymbolMap = getAmountSymbolMap(initialInput);

        for (Map.Entry<String, Integer> entry : amountSymbolMap.entrySet()) {
            if (amountOfValues.containsKey(entry.getKey())) {
                amountOfValues.put(entry.getKey(), amountOfValues.get(entry.getKey()) + entry.getValue());
            } else {
                amountOfValues.put(entry.getKey(), entry.getValue());
            }
        }
    }

    private Map<String, Integer> getAmountSymbolMap(String initialInput) {
        Map<String, Integer> symbolAmountMap;
        if (intputsStorage.isInputExist(initialInput)) {
            symbolAmountMap = intputsStorage.getValues(initialInput);
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

    public Map<String, Integer> getResult() {
        return amountOfValues;
    }
}
