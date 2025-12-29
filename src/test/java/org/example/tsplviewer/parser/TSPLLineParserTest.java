package org.example.tsplviewer.parser;

import org.example.tsplviewer.model.TSPLCommand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TSPLLineParserTest {

    private final TSPLLineParser parser = new TSPLLineParser();

    @Test
    void parseTextCommandString() {
        TSPLCommand cmd = parser.parseLine(
                "TEXT 25,318,\"0\",0,10,10,\"Counter:\"",
                true
        );

        assertEquals("TEXT", cmd.getName());
        assertEquals(List.of("25", "318", "\"0\"", "0", "10", "10", "\"Counter:\""), cmd.getParams());
    }
}
