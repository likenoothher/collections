package com.foxminded.charcounter.output;

import java.util.Map;

public class ConsoleFormatter<K, V> implements OutputFormatter<K, V> {

    @Override
    public boolean isEmpty(Map<K, V> result) {
        return result.isEmpty();
    }

    @Override
    public void processResult(Map<K, V> result) {
        if (result.isEmpty()) {
            System.out.println("There is no characters in the string");
            return;
        }

        result.forEach((key, value) -> System.out.println("\"" + key + "\"" + " - " + value));
        System.out.println("--------------------------------------------------------");
    }
}
