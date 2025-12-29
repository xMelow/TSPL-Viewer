package org.example.tsplviewer.parser;

import org.example.tsplviewer.model.*;
import org.example.tsplviewer.model.commands.*;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.List;

public class TSPLParser {

    private final TSPLLineParser lineParser = new TSPLLineParser();

    public List<TSPLCommand> parse(String tspl) {
        List<TSPLCommand> commands = new ArrayList<>();
        boolean clsSeen = false;

        for (String line: tspl.split("\\r?\\n")) {
            if (line.toUpperCase().contains("CLS")) {
                clsSeen = true;
            }
            commands.add(lineParser.parseLine(line, clsSeen));
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
