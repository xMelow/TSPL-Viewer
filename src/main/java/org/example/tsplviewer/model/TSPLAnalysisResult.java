package org.example.tsplviewer.model;

import org.example.tsplviewer.model.command.TSPLCommand;

import java.util.List;

public record TSPLAnalysisResult (
    List<TSPLCommand> drawCommands,
    List<TSPLCommand> settingsCommands,
    List<TSPLCommand> codeCommands,
    List<ValidationError> errors
) {}
