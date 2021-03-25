package com.foxminded.charcounter.output;

import java.util.Map;

public interface OutputWriter<K, V> {

    void printResult(Map<K, V> result);

}
