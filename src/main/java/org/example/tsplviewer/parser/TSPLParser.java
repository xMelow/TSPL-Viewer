package org.example.tsplviewer.parser;

import org.example.tsplviewer.model.*;

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

    public List<String> validate(String tspl) {
        // check for:
        // missing params
        // unknown commands
        // invalid coordinates
        // missing ""
        List<String> errors = new ArrayList<>();

        return errors;
    }
}
