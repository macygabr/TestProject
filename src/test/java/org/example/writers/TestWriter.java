package org.example.writers;

import org.example.models.Type;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertThrows;

public class TestWriter {
    private final Writer writer = new Writer();

    @Test
    public void testUnknown() {
//        assertThrows(IOException.class, () -> {
//            writer.write(Type.STRING, "/test/estse/esresf");
//        });
    }
}
