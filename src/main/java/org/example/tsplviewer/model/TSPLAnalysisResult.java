package org.example.tsplviewer.model;

import java.util.List;

public record TSPLAnalysisResult (
    List<TSPLCommand> drawCommands,
    List<TSPLCommand> printCommands,
    List<TSPLCommand> codeCommands,
    List<ValidationError> errors
) {}
