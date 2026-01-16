package org.example.tsplviewer.model.command.print;

import org.example.tsplviewer.model.command.CommandType;
import org.example.tsplviewer.model.command.TSPLCommand;

import java.util.List;

public class GapCommand extends TSPLCommand {

    private float gapDistance;
    private float offsetDistance;

    public GapCommand(String name, List<String> params, CommandType type) {
        super(name, params, type);

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
