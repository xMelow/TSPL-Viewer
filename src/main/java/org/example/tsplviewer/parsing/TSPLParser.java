package org.example.tsplviewer.parsing;

import org.example.tsplviewer.model.command.TSPLCommand;

import java.util.ArrayList;
import java.util.List;

public class TSPLParser {

    private final TSPLLineParser lineParser = new TSPLLineParser();

    public List<TSPLCommand> parse(String tspl) {
        List<TSPLCommand> commands = new ArrayList<>();

        for (String line: tspl.split("\\r?\\n")) {
            commands.add(lineParser.parseLine(line));
        }
        return commands;
    }
}
