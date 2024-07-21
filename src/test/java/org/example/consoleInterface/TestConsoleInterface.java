package org.example.consoleInterface;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class TestConsoleInterface {

    private ConsoleInterface consoleInterface;

    @Test
    public void testRead() throws IOException {
        ArrayList<String> files = new ArrayList<>();
        files.add("src/test/resources/testsFiles/in1.txt");
        files.add("src/test/resources/testsFiles/in2.txt");
        String output = "src/test/resources/resultFiles";
        consoleInterface = new ConsoleInterface(files, output, null, false, false, false);
        consoleInterface.run();
    }


    @Test
    public void testExcept() {
        ArrayList<String> files = new ArrayList<>();
        files.add("src/test/resources/testsFiles/123.txt");
        IOException thrown = assertThrows(IOException.class, () -> {
            consoleInterface = new ConsoleInterface(files, null, null, false, false, false);
            consoleInterface.run();
        });
        assertEquals("No such file or directory", thrown.getMessage());
    }
}
