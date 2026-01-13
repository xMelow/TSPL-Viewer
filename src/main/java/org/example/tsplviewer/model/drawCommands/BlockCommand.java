package org.example.tsplviewer.model.drawCommands;

import org.example.tsplviewer.model.CommandType;
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
    private int space;
    private int align;
    private int fit;
    private String content;

    public BlockCommand(String name, List<String> params, CommandType type) {
        super(name, params, type);

        this.x = parseInt(params, 0);
        this.y = parseInt(params, 1);
        this.width = parseInt(params, 2);
        this.height = parseInt(params, 3);
        this.font = params.get(4);
        this.rotation = parseInt(params, 5);
        this.xMultiplication = parseInt(params, 6);
        this.yMultiplication = parseInt(params, 7);

        this.space = safeParseInt(params, 8, 0);
        this.align = safeParseInt(params, 9, 0);
        this.fit = safeParseInt(params, 10, 0);

        this.content = params.getLast();
    }

    private int safeParseInt(List<String> params, int index, int defaultValue) {
        if (index >= params.size() - 1) return defaultValue;

        try {
            return Integer.parseInt(params.get(index).replace("\"", ""));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public int getSpace() {
        return space;
    }

    public int getAlign() {
        return align;
    }

    public int getFit() {
        return fit;
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

    @Override
    public int minParams() {
        return 9;
    }

    @Override
    public int maxParams() {
        return 12;
    }
}
