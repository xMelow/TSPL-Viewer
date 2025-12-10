package org.example.tsplviewer.model.commands;

import org.example.tsplviewer.model.TSPLCommand;

import java.util.List;

public class BoxCommand extends TSPLCommand {

    private int x;
    private int y;
    private int xEnd;
    private int yEnd;
    private int thickness;

    public BoxCommand(String name, List<String> params) {
        super(name, params);

        this.x = Integer.parseInt(params.getFirst());
        this.y = Integer.parseInt(params.get(1));
        this.xEnd = Integer.parseInt(params.get(2));
        this.yEnd = Integer.parseInt(params.get(3));
        this.thickness = Integer.parseInt(params.get(4));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getyEnd() {
        return yEnd;
    }

    public int getxEnd() {
        return xEnd;
    }

    public int getThickness() {
        return thickness;
    }
}
