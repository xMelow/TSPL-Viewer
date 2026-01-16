package org.example.tsplviewer.model.command.code;

import org.example.tsplviewer.model.command.CommandType;
import org.example.tsplviewer.model.command.TSPLCommand;

import java.util.List;

public class InputCommand extends TSPLCommand {

    private String prompt;
    private String variableName;

    public InputCommand(String name, List<String> params, CommandType type) {
        super(name, params, type);

        this.prompt = params.getFirst();
        this.variableName = params.get(1);
    }

    public String getPrompt() {
        return prompt;
    }

    public String getVariableName() {
        return variableName;
    }
}
