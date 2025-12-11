package org.example.tsplviewer.model.commands;

import org.example.tsplviewer.model.TSPLCommand;

import java.util.List;

public class CounterCommand extends TSPLCommand {

    private String counterName;
    private int value;
    private int increment;

    public CounterCommand(String name, List<String> params) {
        super(name, params);

//        this.counterName =
    }
}
