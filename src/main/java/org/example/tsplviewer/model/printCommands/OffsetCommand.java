package org.example.tsplviewer.model.printCommands;

import org.example.tsplviewer.model.TSPLCommand;

import java.util.List;

public class OffsetCommand extends TSPLCommand {

    private int distance;

    public OffsetCommand(String name, List<String> params) {
        super(name, params);

        this.distance = parseParam(params.getFirst());
    }

    private int parseParam(String param) {
        return Integer.parseInt(param.replaceAll("[^0-9]", ""));
    }

    public float getDistance() {
        return distance;
    }

    @Override
    public int minParams() {
        return 1;
    }

    @Override
    public int maxParams() {
        return 1;
    }
}
