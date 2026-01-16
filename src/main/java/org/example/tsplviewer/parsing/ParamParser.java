package org.example.tsplviewer.parsing;

import java.util.ArrayList;
import java.util.List;

public class ParamParser {

    public List<String> parse(String line, String commandName) {
        if (line.isBlank()) return List.of();

        String clean = line;

        if (line.contains("mm")) clean = removeMetrics(line);

        String paramPart = clean.replaceFirst(commandName, "").trim();

        if (paramPart.isEmpty()) return List.of();

        if (paramPart.contains("SET") && commandName.contains("COUNTER")) return  new ArrayList<>(List.of(paramPart.replace("SET  ", "").split(" ")));
        if (paramPart.contains("SET")) return new ArrayList<>(List.of(paramPart.replace("SET  ", "")));

        return new ArrayList<>(List.of(paramPart.split(",")));
    }

    private String removeMetrics(String s) {
        return s.replace(" mm", "").trim();
    }
}
