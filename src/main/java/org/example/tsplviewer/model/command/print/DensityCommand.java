package org.example.tsplviewer.model.command.print;

import org.example.tsplviewer.model.command.CommandType;
import org.example.tsplviewer.model.command.TSPLCommand;

import java.util.List;

public class DensityCommand extends TSPLCommand {

    private int density;

    public DensityCommand(String name, List<String> params, CommandType type) {
        super(name, params, type);

        this.density = parseParam(params.getFirst());
    }

    private int parseParam(String param) {
        return Integer.parseInt(param.replaceAll("[^0-9]", ""));
    }

    public float getDensity() {
        return density;
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
