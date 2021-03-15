package com.foxminded;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CharCounterApp {
    private static final InputsCacheStorage INPUTS_CACHE_STORAGE = new InputsCacheStorage();

    public static void main(String[] args) throws IOException {
        List<String> inputs = new ArrayList<>();
        inputs.add(" ");
        buildApp(inputs);

    }

    public static void buildApp(List<String> inputs) throws IOException {

        InputsCharCounter icc = new InputsCharCounter(INPUTS_CACHE_STORAGE);

        for (String input : inputs) {
            icc.addInputToCounter(input);
        }

        for (Map.Entry<String, Integer> entry : icc.getAmountOfSymbols().entrySet()) {
            System.out.println(String.format("\"%s\" - %d", entry.getKey(), entry.getValue()));

        }
    }
}
