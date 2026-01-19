package org.example.tsplviewer.parsing;

import org.example.tsplviewer.model.command.TSPLCommand;

import java.util.List;

public class TSPLLineParser {

    private final CommandFactory factory = new CommandFactory();
    private final ParamParser paramParser = new ParamParser();

    public TSPLCommand parseLine(String line) {
        System.out.println(line);
        String name = extractCommandName(line);
        List<String> params = paramParser.parse(line, name);

        return factory.create(name, params);
    }

    private String extractCommandName(String line) {
        if (line.isBlank()) return "";

        String[] parts = line.trim().split("\\s+");

        return parts[0].equalsIgnoreCase("SET") && parts.length > 1
                ? parts[1]
                : parts[0];
    }
}
