package com.foxminded.charcounter.output;

import java.util.Map;

public class ConsoleWriter<K, V> implements OutputWriter<K, V> {

    @Override
    public void printResult(Map<K, V> result) {
        if (result.isEmpty()) {
            System.out.println("There is no characters in the string");
            return;
        }

        result.forEach((key, value) -> System.out.println("\"" + key + "\"" + " - " + value));
        System.out.println("--------------------------------------------------------");
    }
}
