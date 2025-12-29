package org.example.tsplviewer.parser;

import java.util.List;

public class ParamParser {

    public List<String> parse(String line, String commandName) {
        if (line.isBlank()) return List.of();

        String clean = line;

        if (line.contains("mm")) clean = removeMetrics(line);

        String paramPart = clean.replaceFirst(commandName, "").trim();

        if (paramPart.isEmpty()) return List.of();

        if (paramPart.contains("SET") && commandName.contains("COUNTER")) return List.of(paramPart.replace("SET  ", "").split(" "));

        if (paramPart.contains("SET")) return List.of(paramPart.replace("SET  ", ""));

        return List.of(paramPart.split(","));
    }

    private String removeMetrics(String s) {
        return s.replace(" mm", "").trim();
    }
}
