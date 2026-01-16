package org.example.tsplviewer.model.command.code;

import org.example.tsplviewer.model.command.CommandType;
import org.example.tsplviewer.model.command.TSPLCommand;

import java.util.List;

public class CounterCommand extends TSPLCommand {

    private String counterName;
    private int value;
    private int increment;

    public CounterCommand(String name, List<String> params, CommandType type) {
        super(name, params, type);

//        this.counterName =
    }
}
