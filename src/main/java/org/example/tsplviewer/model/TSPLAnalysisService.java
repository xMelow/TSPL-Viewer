package org.example.tsplviewer.model;

import org.example.tsplviewer.parser.TSPLParser;
import org.example.tsplviewer.validator.TSPLValidator;

import java.util.ArrayList;
import java.util.List;

public class TSPLAnalysisService {

    private final TSPLParser parser = new TSPLParser();
    private final TSPLValidator validator = new TSPLValidator();

    public TSPLAnalysisResult analyze(String tspl) {
        List<TSPLCommand> commands = parser.parse(tspl);
        List<ValidationError> errors = validator.validate(commands);
        List<TSPLCommand> drawCommands = findCommands(commands, CommandType.DRAW);
        List<TSPLCommand> settingCommands = findCommands(commands, CommandType.SETTING);
        List<TSPLCommand> codeCommands = findCommands(commands, CommandType.CODE);

        return new TSPLAnalysisResult(drawCommands, settingCommands, codeCommands, errors);
    }

    private List<TSPLCommand> findCommands(List<TSPLCommand> commands, CommandType filter) {
        List<TSPLCommand> result = new ArrayList<>();

        for (TSPLCommand command : commands) {
            if (command.getType() == filter) {
                result.add(command);
            }
        }
        return result;
    }
}
