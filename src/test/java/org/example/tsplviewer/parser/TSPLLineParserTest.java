package org.example.tsplviewer.parser;

import org.example.tsplviewer.model.TSPLCommand;
import org.example.tsplviewer.model.commands.BarCommand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TSPLLineParserTest {

    private final TSPLLineParser parser = new TSPLLineParser();

    @Test
    void parseSizeCommandString() {
        TSPLCommand cmd = parser.parseLine(
                "SIZE 28 mm,55 mm"
        );

        assertEquals("SIZE", cmd.getName());
        assertEquals(List.of("28", "55"), cmd.getParams());
    }

    // add all print commands

    @Test
    void parseTextCommandString() {
        TSPLCommand cmd = parser.parseLine(
                "TEXT 25,318,\"0\",0,10,10,\"Counter:\""
        );

        assertEquals("TEXT", cmd.getName());
        assertEquals(List.of("25", "318", "\"0\"", "0", "10", "10", "\"Counter:\""), cmd.getParams());
    }

    @Test
    void parseBarCommandString() {
        TSPLCommand cmd = parser.parseLine(
                "BAR 33,135,1199,7"
        );

        assertEquals("BAR", cmd.getName());
        assertEquals(List.of("33", "135", "1199", "7"), cmd.getParams());
    }

    @Test
    void parseBoxCommandString() {
        TSPLCommand cmd = parser.parseLine(
                "BOX 33,178,1232,890,6"
        );

        assertEquals("BOX", cmd.getName());
        assertEquals(List.of("33", "178", "1232", "890", "6"), cmd.getParams());
    }

    @Test
    void parseQRCodeCommandString() {
        TSPLCommand cmd = parser.parseLine(
                "QRCODE 938,951,L,14,A,0,M2,S7,\"123456789012\""
        );

        assertEquals("QRCODE", cmd.getName());
        assertEquals(List.of("938", "951", "L", "14", "A", "0", "M2", "S7", "\"123456789012\""), cmd.getParams());
    }

    @Test
    void parseCircleCommandString() {
        TSPLCommand cmd = parser.parseLine(
                "CIRCLE 933,590,260,12"
        );

        assertEquals("CIRCLE", cmd.getName());
        assertEquals(List.of("933", "590", "260", "12"), cmd.getParams());
    }

    @Test
    void parseBarcodeCommandString() {
        TSPLCommand cmd = parser.parseLine(
                "BARCODE 64,488,\"128M\",116,2,0,2,4,\"!105123456789012\""
        );

        assertEquals("BARCODE", cmd.getName());
        assertEquals(List.of("64", "488", "\"128M\"", "116", "2", "0", "2", "4", "\"!105123456789012\""), cmd.getParams());
    }
}
