package com.foxminded;

import gnu.trove.iterator.TCharLongIterator;
import gnu.trove.map.TCharLongMap;
import gnu.trove.map.hash.TCharLongHashMap;


public class InputsCharCounter {
    private TCharLongMap symbolAmountResult = new TCharLongHashMap();
    private InputCacheStorage inputStorage;

    public InputsCharCounter(InputCacheStorage inputStorage) {
        this.inputStorage = inputStorage;
    }

    public void addInputToCounter(String initialInput) {
        TCharLongMap amountSymbolMap = getSymbolAmountMap(initialInput);

        for (TCharLongIterator it = amountSymbolMap.iterator(); it.hasNext(); ) {
            it.advance();
            if (symbolAmountResult.containsKey(it.key())) {
                symbolAmountResult.put(it.key(), symbolAmountResult.get(it.key()) + it.value());
            } else {
                symbolAmountResult.put(it.key(), it.value());
            }
        }
    }

    private TCharLongMap getSymbolAmountMap(String initialInput) {
        if (inputStorage.isInputExist(initialInput)) {
            return inputStorage.getCachedInput(initialInput);
        }
        TCharLongMap symbolAmountMap = createSymbolAmountMap(initialInput);
        inputStorage.addToCache(initialInput, symbolAmountMap);
        return symbolAmountMap;

    }

    private TCharLongMap createSymbolAmountMap(String initialInput) {
        TCharLongMap symbolAmountMap = new TCharLongHashMap();
        if (initialInput.equals("")) {
            symbolAmountMap.put(Character.MIN_VALUE, 1);
            return symbolAmountMap;
        }
        fillSymbolAmountMap(symbolAmountMap, initialInput);
        return symbolAmountMap;

    }

    private void fillSymbolAmountMap(TCharLongMap symbolAmountMap, String initialInput) {
        char[] charArray = initialInput.toCharArray();
        for (char symbol : charArray) {
            if (symbolAmountMap.containsKey(symbol)) {
                symbolAmountMap.put(symbol, symbolAmountMap.get(symbol) + 1);
            } else {
                symbolAmountMap.put(symbol, 1);
            }
        }
    }

    public TCharLongMap getSymbolAmountResult() {
        return symbolAmountResult;
    }
}
