package org.example.tsplviewer.model;

import java.util.List;

public class TSPLCommand {

    private String name;
    private List<String> params;

    public TSPLCommand(String name, List<String> params) {
        this.name = name;
        this.params = params;
    }

    public String getName() {
        return name;
    }

    public List<String> getParams() {
        return params;
    }

    @Override
    public String toString() {
        return "command name: " + name + " Params: " + params;
    }
}
