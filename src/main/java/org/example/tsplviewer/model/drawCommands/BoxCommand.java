package org.example.tsplviewer.model.drawCommands;

import org.example.tsplviewer.model.CommandType;
import org.example.tsplviewer.model.TSPLCommand;

import java.util.List;

public class BoxCommand extends TSPLCommand {

    private int x;
    private int y;
    private int xEnd;
    private int yEnd;
    private int thickness;

    public BoxCommand(String name, List<String> params, CommandType type) {
        super(name, params, type);

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

    @Override
    public int minParams() {
        return 5;
    }

    @Override
    public int maxParams() {
        return 6;
    }
}
