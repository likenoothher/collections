package com.foxminded;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BuildAppTest {
    @Mock
    BufferedReader reader;

    @Test
    public void testOutput() throws IOException {
        List<String> inputString = new ArrayList<>();
        inputString.add("Hello world!");

        String expected = String.format(""+
                "\" \" - 1\r\n"+
                "\"!\" - 1\r\n"+
                "\"H\" - 1\r\n"+
                "\"d\" - 1\r\n"+
                "\"e\" - 1\r\n"+
                "\"l\" - 3\r\n"+
                "\"o\" - 2\r\n"+
                "\"r\" - 1\r\n"+
                "\"w\" - 1\r\n"
                );

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        BuildApp.buildApp(inputString);
        assertEquals(expected, outputStream.toString());

    }
}
