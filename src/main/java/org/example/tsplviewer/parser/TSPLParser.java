package org.example.tsplviewer.parser;

import org.example.tsplviewer.model.*;
import org.example.tsplviewer.model.commands.*;

import java.util.ArrayList;
import java.util.List;

public class TSPLParser {

     private boolean clsHasAppeared = false;

    public TSPLParser() {}

    public List<TSPLCommand> parse(String tspl) {
        List<TSPLCommand> commands = new ArrayList<>();

        for (String tsplSentence : tspl.split("\\r?\\n")) {
            checkForCLS(tsplSentence);
            if (!clsHasAppeared) {
                commands.add(getPrintCommand(tsplSentence));
            } else {
                commands.add(getDrawCommand(tsplSentence));
            }
        }
        System.out.println(commands);
        return commands;
    }

    private void checkForCLS(String sentence) {
        if (sentence.contains("CLS")) {
            clsHasAppeared = true;
        }
    }

    private TSPLCommand getPrintCommand(String tsplSentence) {
        String commandName = getCommandName(tsplSentence);
        List<String> commandParams = getPrintParams(tsplSentence);
        return createCommand(commandName, commandParams);
    }

    private TSPLCommand getDrawCommand(String tsplSentence) {
        String commandName = getCommandName(tsplSentence);
        List<String> commandParams = getDrawParams(tsplSentence);
        return createCommand(commandName, commandParams);
    }

    private String getCommandName(String sentence) {
        if (sentence.isEmpty()) return "";

        String[] parts = sentence.split("\\s+");

        if (parts[0].equalsIgnoreCase("SET")) {
            if (parts.length >= 2) {
                return parts[1];
            } else {
                return "";
            }
        }
        return parts[0];
    }

    private List<String> getPrintParams(String sentence) {
        String cleanSentence = removeMetrics(sentence);
        return getParams(cleanSentence);
    }

    private List<String> getDrawParams(String sentence) {
        return getParams(sentence);
    }

    private TSPLCommand createCommand(String name, List<String> params) {
        return switch (name.toUpperCase()) {
            case "TEXT" -> new TextCommand(name, params);
            case "BOX" -> new BoxCommand(name, params);
            case "BAR" -> new BarCommand(name, params);
            case "CIRCLE" -> new CircleCommand(name, params);
            case "QRCODE" -> new QRCodeCommand(name, params);
            case "BARCODE" -> new BarcodeCommand(name, params);
            default -> new TSPLCommand(name, params);
        };
    }

    private String removeMetrics(String s) {
        return s.replace(" mm", "").trim();
    }

    private List<String> getParams(String sentence) {
        List<String> result = new ArrayList<>();

        sentence = sentence.trim();
        if (sentence.isEmpty()) return result;

        String commandName = getCommandName(sentence);
        String cleanSentence = sentence.replace(commandName + " ", "").trim();

        if (cleanSentence.length() > 1) {
            String[] parts = cleanSentence.split(",");
            result.addAll(List.of(parts));
        } else {
            result.add(cleanSentence);
        }
        return result;
    }

    public List<String> validate(String tspl) {
        // check for:
        // missing params
        // unknown commands
        // invalid coordinates
        // missing ""
        List<String> errors = new ArrayList<>();

        // return error string list
        return errors;
    }
}
