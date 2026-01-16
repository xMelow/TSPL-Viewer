package org.example.tsplviewer.parsing;

import org.example.tsplviewer.model.command.TSPLCommand;
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

    @Test
    void parseGapCommandString() {
        TSPLCommand cmd = parser.parseLine(
                "GAP 3 mm,0 mm"
        );

        assertEquals("GAP", cmd.getName());
        assertEquals(List.of("3", "0"), cmd.getParams());
    }

    @Test
    void parseReferenceCommandString() {
        TSPLCommand cmd = parser.parseLine(
                "REFERENCE 0,0"
        );

        assertEquals("REFERENCE", cmd.getName());
        assertEquals(List.of("0", "0"), cmd.getParams());
    }

    @Test
    void parseSpeedCommandString() {
        TSPLCommand cmd = parser.parseLine(
                "SPEED 2.0"
        );

        assertEquals("SPEED", cmd.getName());
        assertEquals(List.of("2.0"), cmd.getParams());
    }

    @Test
    void parseDensityCommandString() {
        TSPLCommand cmd = parser.parseLine(
                "DENSITY 8"
        );

        assertEquals("DENSITY", cmd.getName());
        assertEquals(List.of("8"), cmd.getParams());
    }

    @Test
    void parseRibbonCommandString() {
        TSPLCommand cmd = parser.parseLine(
                "SET RIBBON ON"
        );

        assertEquals("RIBBON", cmd.getName());
        assertEquals(List.of("ON"), cmd.getParams());
    }

    @Test
    void parsePeelCommandString() {
        TSPLCommand cmd = parser.parseLine(
                "SET PEEL OFF"
        );

        assertEquals("PEEL", cmd.getName());
        assertEquals(List.of("OFF"), cmd.getParams());
    }

    @Test
    void parseCutterCommandString() {
        TSPLCommand cmd = parser.parseLine(
                "SET CUTTER OFF"
        );

        assertEquals("CUTTER", cmd.getName());
        assertEquals(List.of("OFF"), cmd.getParams());
    }

    @Test
    void parseTearCommandString() {
        TSPLCommand cmd = parser.parseLine(
                "SET TEAR ON"
        );

        assertEquals("TEAR", cmd.getName());
        assertEquals(List.of("ON"), cmd.getParams());
    }

    @Test
    void parseRewindCommandString() {
        TSPLCommand cmd = parser.parseLine(
                "SET REWIND OFF"
        );

        assertEquals("REWIND", cmd.getName());
        assertEquals(List.of("OFF"), cmd.getParams());
    }

    @Test
    void parseDirectionCommandString() {
        TSPLCommand cmd = parser.parseLine(
                "DIRECTION 0,0"
        );

        assertEquals("DIRECTION", cmd.getName());
        assertEquals(List.of("0", "0"), cmd.getParams());
    }

    @Test
    void parseShiftCommandString() {
        TSPLCommand cmd = parser.parseLine(
                "SHIFT 0,0"
        );

        assertEquals("SHIFT", cmd.getName());
        assertEquals(List.of("0", "0"), cmd.getParams());
    }

    @Test
    void parseOffsetCommandString() {
        TSPLCommand cmd = parser.parseLine(
                "OFFSET 0 mm"
        );

        assertEquals("OFFSET", cmd.getName());
        assertEquals(List.of("0"), cmd.getParams());
    }

    @Test
    void parseCounterCommandString() {
        TSPLCommand cmd = parser.parseLine(
                "SET COUNTER @0 +1"
        );

        assertEquals("COUNTER", cmd.getName());
        assertEquals(List.of("@0", "+1"), cmd.getParams());
    }

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
