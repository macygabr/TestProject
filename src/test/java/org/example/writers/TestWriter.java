package org.example.writers;

import org.example.models.Type;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestWriter {
    private Writer writer;

    @Before
    public void init() {
        try {
            writer = new Writer();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testUnknownType() {
        HashMap<Type, ArrayList<String>> map = new HashMap<>();
        map.put(null, null);

        IOException thrown = assertThrows(IOException.class, () -> {
            writer.write(map);
        });
        assertEquals("Unknown type", thrown.getMessage());
    }

    @Test
    public void testNullMap() {
        HashMap<Type, ArrayList<String>> map = new HashMap<>();
        map.put(Type.STRING, null);
        map.put(Type.INTEGER, null);
        map.put(Type.FLOAT, null);

        IOException thrown = assertThrows(IOException.class, () -> {
            writer.write(map);
        });
        assertEquals("Unknown type", thrown.getMessage());
    }
}
