package org.example.tsplviewer.model;

import java.util.List;

public record TSPLAnalysisResult (
    List<TSPLCommand> drawCommands,
    List<TSPLCommand> settingsCommands,
    List<TSPLCommand> codeCommands,
    List<ValidationError> errors
) {}
