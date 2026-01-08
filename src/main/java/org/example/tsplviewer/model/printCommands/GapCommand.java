package org.example.tsplviewer.model.printCommands;

import org.example.tsplviewer.model.TSPLCommand;

import java.util.List;

public class GapCommand extends TSPLCommand {

    private float gapDistance;
    private float offsetDistance;

    public GapCommand(String name, List<String> params) {
        super(name, params);

        this.gapDistance = parseParam(params.getFirst());
        this.offsetDistance = parseParam(params.get(1));
    }

    private float parseParam(String param) {
        return Float.parseFloat(param.replaceAll("[^0-9]", ""));
    }

    public float getGapDistance() {
        return gapDistance;
    }

    public float getOffsetDistance() {
        return offsetDistance;
    }

    @Override
    public int minParams() {
        return 2;
    }

    @Override
    public int maxParams() {
        return 2;
    }
}
