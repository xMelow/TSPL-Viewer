package org.example.tsplviewer.model.commands;

import org.example.tsplviewer.model.TSPLCommand;

import java.util.List;

public class SizeCommand extends TSPLCommand {

    private int width;
    private int height;

    public SizeCommand(String name, List<String> params) {
        super(name, params);

        this.width = parseParam(params.getFirst());
        this.height = parseParam(params.get(1));
    }

    private int parseParam(String param) {
        return Integer.parseInt(param.replaceAll("[^0-9]", ""));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
