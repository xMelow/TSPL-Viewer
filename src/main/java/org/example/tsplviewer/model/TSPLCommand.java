package org.example.tsplviewer.model;

import java.util.List;

public class TSPLCommand {

    private String type;
    private List<String> params;

    // constructor

    public TSPLCommand(String type, List<String> params) {
        this.type = type;
        this.params = params;
    }

    // getters and setters
}
