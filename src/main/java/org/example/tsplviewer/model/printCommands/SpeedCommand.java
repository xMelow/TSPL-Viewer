package org.example.tsplviewer.model.printCommands;

import org.example.tsplviewer.model.TSPLCommand;

import java.util.List;

public class SpeedCommand extends TSPLCommand {

    private float speed;

    public SpeedCommand(String name, List<String> params) {
        super(name, params);

        this.speed = parseParam(params.getFirst());
    }

    private float parseParam(String param) {
        return Float.parseFloat(param.replaceAll("[^0-9]", ""));
    }

    public float getSpeed() {
        return speed;
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
