package com.foxminded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BuildApp {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> inputs = new ArrayList<>();
        while (true) {
            String input = reader.readLine();
            if (input.equals("exit")) {
                reader.close();
                break;
            }
            inputs.add(input);
        }
        buildApp(inputs);

    }

    public static void buildApp(List<String> inputs) throws IOException {

        CacheInputsStorage cache = new CacheInputsStorage();
        InputCharCounter cc = new InputCharCounter(cache);

        for (String input: inputs) {
            cc.addInputResultMap(input);
        }

        for (Map.Entry<String, Integer> entry : cc.getResult().entrySet()) {
            System.out.println(String.format("\"%s\" - %d",  entry.getKey(), entry.getValue()));

        }
    }
}
