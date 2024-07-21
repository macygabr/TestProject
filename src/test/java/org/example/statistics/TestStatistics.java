package org.example.statistics;

import lombok.Setter;
import org.example.models.Type;
import org.example.writers.Writer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestStatistics {

    Statistics statistics = new Statistics();

    @Before
    public void init() {

    }

    @Test
    public void testUnknownType() {
        HashMap<Type, ArrayList<String>> map = new HashMap<>();
        map.put(null, null);
        statistics.setShortFormat(true);
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            statistics.printInfo(map);
        });
        assertEquals("Error with read files", thrown.getMessage());
    }
}
