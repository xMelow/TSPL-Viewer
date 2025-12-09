package org.example.tsplviewer.model;

import java.util.List;

public class BarCommand extends TSPLCommand {

    private int x;
    private int y;
    private int width;
    private int height;

    public BarCommand(String name, List<String> params) {
        super(name, params);

        this.x = Integer.parseInt(params.getFirst());
        this.y = Integer.parseInt(params.get(1));
        this.width = Integer.parseInt(params.get(2));
        this.height = Integer.parseInt(params.get(3));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
