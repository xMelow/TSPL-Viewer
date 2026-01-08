package org.example.tsplviewer.model.printCommands;

import org.example.tsplviewer.model.TSPLCommand;

import java.util.List;

public class SizeCommand extends TSPLCommand {

    private float width;
    private float height;

    public SizeCommand(String name, List<String> params) {
        super(name, params);

        this.width = parseParam(params.getFirst());
        this.height = parseParam(params.get(1));
    }

    private float parseParam(String param) {
        return Float.parseFloat(param.replaceAll("[^0-9]", ""));
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
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
