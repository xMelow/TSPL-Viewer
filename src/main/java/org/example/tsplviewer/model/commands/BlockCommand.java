package org.example.tsplviewer.model.commands;

import org.example.tsplviewer.model.TSPLCommand;

import java.util.List;

public class BlockCommand extends TSPLCommand {

    private int x;
    private int y;
    private int width;
    private int height;
    private String font;
    private int rotation;
    private int xMultiplication;
    private int yMultiplication;
    private String content;


    public BlockCommand(String name, List<String> params) {
        super(name, params);

        this.x = Integer.parseInt(params.getFirst());
        this.y = Integer.parseInt(params.get(1));
        this.width = Integer.parseInt(params.get(2));
        this.height = Integer.parseInt(params.get(3));
        this.font = params.get(4);
        this.rotation = Integer.parseInt(params.get(5));
        this.xMultiplication = Integer.parseInt(params.get(6));
        this.yMultiplication = Integer.parseInt(params.get(7));
        this.content = params.get(8).replace("\"", "");
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

    public String getFont() {
        return font;
    }

    public int getRotation() {
        return rotation;
    }

    public int getxMultiplication() {
        return xMultiplication;
    }

    public int getyMultiplication() {
        return yMultiplication;
    }

    public String getContent() {
        return content;
    }
}
