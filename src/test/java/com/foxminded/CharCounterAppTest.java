package com.foxminded;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CharCounterAppTest {

    @Test
    public void whenSingleInput_thenSymbolsAmountEqualsSymbolsAmountOfInput() throws IOException {
        List<String> inputString = new ArrayList<>();
        inputString.add("Hello world!");

        String expected = String.format("" +
            "\" \" - 1\r\n" +
            "\"!\" - 1\r\n" +
            "\"H\" - 1\r\n" +
            "\"d\" - 1\r\n" +
            "\"e\" - 1\r\n" +
            "\"l\" - 3\r\n" +
            "\"o\" - 2\r\n" +
            "\"r\" - 1\r\n" +
            "\"w\" - 1\r\n"
        );

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        CharCounterApp.printSymbolAmount(inputString);
        assertEquals(expected, outputStream.toString());

    }

    @Test
    public void whenMultiplyInput_thenSymbolsAmountEqualsSymbolsAmountOfEveryInput() throws IOException {
        List<String> inputString = new ArrayList<>();
        inputString.add("Hello world!");
        inputString.add("Hello world!");

        String expected = String.format("" +
            "\" \" - 2\r\n" +
            "\"!\" - 2\r\n" +
            "\"H\" - 2\r\n" +
            "\"d\" - 2\r\n" +
            "\"e\" - 2\r\n" +
            "\"l\" - 6\r\n" +
            "\"o\" - 4\r\n" +
            "\"r\" - 2\r\n" +
            "\"w\" - 2\r\n"
        );

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        CharCounterApp.printSymbolAmount(inputString);
        assertEquals(expected, outputStream.toString());

    }
}
