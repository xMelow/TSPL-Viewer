package org.example.tsplviewer.model.command.print;

import org.example.tsplviewer.model.command.CommandType;
import org.example.tsplviewer.model.command.TSPLCommand;

import java.util.List;

public class DirectionCommand extends TSPLCommand {

    private int direction;
    private int mirror;

    public DirectionCommand(String name, List<String> params, CommandType type) {
        super(name, params, type);

        this.direction = parseParam(params.getFirst());
        this.mirror = parseParam(params.get(1));
    }

    private int parseParam(String param) {
        return Integer.parseInt(param.replaceAll("[^0-9]", ""));
    }

    public int getDirection() {
        return direction;
    }

    public int getMirror() {
        return mirror;
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
