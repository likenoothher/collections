package com.foxminded.charcounter.output;

import java.util.Map;

public interface OutputFormatter<K, V> {
    boolean isEmpty(Map<K, V> result);

    void processResult(Map<K, V> result);

}
