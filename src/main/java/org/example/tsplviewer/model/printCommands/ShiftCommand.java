package org.example.tsplviewer.model.printCommands;

import org.example.tsplviewer.model.TSPLCommand;

import java.util.List;

public class ShiftCommand extends TSPLCommand {

    private int x;
    private int y;

    public ShiftCommand(String name, List<String> params) {
        super(name, params);

        this.x = parseParam(params.getFirst());
        this.y = parseParam(params.get(1));
    }

    private int parseParam(String param) {
        return Integer.parseInt(param.replaceAll("[^0-9]", ""));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int minParams() {
        return 1;
    }

    @Override
    public int maxParams() {
        return 2;
    }
}
