package org.example.readers;

import org.example.models.Type;
import org.junit.Assert;
import org.junit.Test;


public class TestParser {
    private final Parser parser = new Parser();

    @Test
    public void testInteger() {
        for (int i = -10; i <= 10; i++) {
            Assert.assertEquals(parser.parse(String.valueOf(i)), Type.INTEGER);
        }
    }

    @Test
    public void testFloat() {
        for (int i = -10; i <= 10; i++) {
            Assert.assertEquals(parser.parse(String.valueOf(i + 0.1)), Type.FLOAT);
        }
    }

    @Test
    public void testString() {
        for (int i = -10; i <= 10; i++) {
            Assert.assertEquals(parser.parse(String.valueOf("test#" + i)), Type.STRING);
        }
    }

    @Test
    public void testUnknown() {
        Assert.assertEquals(parser.parse(null), Type.UNKNOWN);
    }
}
