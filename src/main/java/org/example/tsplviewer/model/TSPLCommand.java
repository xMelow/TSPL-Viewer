package org.example.tsplviewer.model;

import java.util.List;

public class TSPLCommand {

    protected String name;
    private List<String> params;
    private CommandType type;

    public TSPLCommand(String name, List<String> params, CommandType type) {
        this.name = name;
        this.params = params;
        this.type = type;
    }

    protected int parseInt(List<String> params, int index) {
        return Integer.parseInt(params.get(index));
    }

    protected int parseInt(List<String> params, int index, int defaultValue) {
        return index < params.size()
                ? Integer.parseInt(params.get(index))
                : defaultValue;
    }

    protected String stripQuotes(List<String> params, int index) {
        return params.get(index).replace("\"", "");
    }

    public CommandType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public List<String> getParams() {
        return params;
    }

    public int minParams() {
        return 0;
    }

    public int maxParams() {
        return Integer.MAX_VALUE;
    }

    @Override
    public String toString() {
        return "command name: " + name + " Params: " + params;
    }
}
