package org.example.tsplviewer.model;

import java.util.List;

public class TextCommand extends TSPLCommand {

    private int x;
    private int y;
    // change datatype?
    private String font;
    private int rotation;
    private int xMultiplication;
    private int yMultiplication;
    private String content;

    public TextCommand(String name, List<String> params) {
        super(name, params);

        this.x = Integer.parseInt(params.get(0));
        this.y = Integer.parseInt(params.get(1));
        this.font = params.get(2);
        this.rotation = Integer.parseInt(params.get(3));
        this.xMultiplication = Integer.parseInt(params.get(4));
        this.yMultiplication = Integer.parseInt(params.get(5));
        this.content = params.get(6).replace("\"", "");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getFont() {
        return font;
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

    @Override
    public String toString() {
        return name + ": (" + x + "," + y + ") " + content;
    }
}
