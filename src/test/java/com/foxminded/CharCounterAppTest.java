package com.foxminded;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CharCounterAppTest {

    @Test
    public void whenSingleInput_thenPrintSymbolsAmountAlphabetOrder() throws IOException {
        String inputString = "Hello world!";

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
    public void whenFewInputs_thenPrintEveryInputSymbolsAmountAlphabetOrder() throws IOException {
        String firstInputString = "Hello world!";
        String secondInputString = "qwerty";

        String expected = String.format("" +
            "\" \" - 1\r\n" +
            "\"!\" - 1\r\n" +
            "\"H\" - 1\r\n" +
            "\"d\" - 1\r\n" +
            "\"e\" - 1\r\n" +
            "\"l\" - 3\r\n" +
            "\"o\" - 2\r\n" +
            "\"r\" - 1\r\n" +
            "\"w\" - 1\r\n" +
            "\"e\" - 1\r\n" +
            "\"q\" - 1\r\n" +
            "\"r\" - 1\r\n" +
            "\"t\" - 1\r\n" +
            "\"w\" - 1\r\n" +
            "\"y\" - 1\r\n"

        );

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        CharCounterApp.printSymbolAmount(firstInputString);
        CharCounterApp.printSymbolAmount(secondInputString);
        assertEquals(expected, outputStream.toString());

    }

    @Test
    public void whenFewInputsOneIsEmpty_thenPrintNotEmptyInputSymbolsAmountAndMessageForEmpty() throws IOException {
        String firstInputString = "Hello world!";
        String secondInputString = "qwerty";
        String thirdInputString = "";

        String expected = String.format("" +
            "\" \" - 1\r\n" +
            "\"!\" - 1\r\n" +
            "\"H\" - 1\r\n" +
            "\"d\" - 1\r\n" +
            "\"e\" - 1\r\n" +
            "\"l\" - 3\r\n" +
            "\"o\" - 2\r\n" +
            "\"r\" - 1\r\n" +
            "\"w\" - 1\r\n" +
            "\"e\" - 1\r\n" +
            "\"q\" - 1\r\n" +
            "\"r\" - 1\r\n" +
            "\"t\" - 1\r\n" +
            "\"w\" - 1\r\n" +
            "\"y\" - 1\r\n" +
            "There is no characters in the string\r\n"

        );

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        CharCounterApp.printSymbolAmount(firstInputString);
        CharCounterApp.printSymbolAmount(secondInputString);
        CharCounterApp.printSymbolAmount(thirdInputString);
        assertEquals(expected, outputStream.toString());

    }

    @Test
    public void whenEmptyInput_thenNoCharactersInStringMessage() throws IOException {
        String inputString = "";

        String expected = "There is no characters in the string\r\n";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        CharCounterApp.printSymbolAmount(inputString);
        assertEquals(expected, outputStream.toString());

    }
}
