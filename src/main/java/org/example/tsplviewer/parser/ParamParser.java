package org.example.tsplviewer.parser;

import java.util.List;

public class ParamParser {

    public List<String> parse(String line, String commandName, boolean drawMode) {
        // todo: types of parsing for type of command
        if (line.isBlank()) return List.of();

        String clean = drawMode ? line : removeMetrics(line);
        String paramPart = clean.replaceFirst(commandName, "").trim();

        if (paramPart.isEmpty()) return List.of();

        return List.of(paramPart.split(","));
    }

    private String removeMetrics(String s) {
        return s.replace(" mm", "").trim();
    }
}
