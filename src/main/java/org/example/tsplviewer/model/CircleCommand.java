package org.example.tsplviewer.model;

import java.util.List;

public class CircleCommand extends TSPLCommand{

    private int xStart;
    private int yStart;
    private int diameter;
    private int thickness;

    public CircleCommand(String name, List<String> params) {
        super(name, params);

        this.xStart = Integer.parseInt(params.getFirst());
        this.yStart = Integer.parseInt(params.get(1));
        this.diameter = Integer.parseInt(params.get(2));
        this.thickness = Integer.parseInt(params.get(3));
    }

    public int getxStart() {
        return xStart;
    }

    public int getyStart() {
        return yStart;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getThickness() {
        return thickness;
    }
}
