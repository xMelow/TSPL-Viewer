package org.example.tsplviewer.model;

import org.example.tsplviewer.parser.TSPLParser;
import org.example.tsplviewer.validator.TSPLValidator;

import java.util.List;

public class TSPLAnalysisService {

    private final TSPLParser parser = new TSPLParser();
    private final TSPLValidator validator = new TSPLValidator();

    public TSPLAnalysisResult analyze(String tspl) {
        List<TSPLCommand> commands = parser.parse(tspl);
        List<ValidationError> errors = validator.validate(commands);

        List<TSPLCommand> drawCommands = findDrawCommands(commands);
        List<TSPLCommand> printCommands = findPrintCommands(commands);
        List<TSPLCommand> codeCommands = findCodeCommands(commands);

        return new TSPLAnalysisResult(drawCommands, printCommands, codeCommands, errors);
    }
}
