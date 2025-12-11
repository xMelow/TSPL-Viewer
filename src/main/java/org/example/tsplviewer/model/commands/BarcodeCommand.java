package org.example.tsplviewer.model.commands;

import org.example.tsplviewer.model.TSPLCommand;

import java.util.List;

public class BarcodeCommand extends TSPLCommand {

    private int x;
    private int y;
    private String codeType;
    private int height;
    private int humanReadable;
    private int rotation;
    private int narrow;
    private int wide;
    private String content;

    public BarcodeCommand(String name, List<String> params) {
        super(name, params);

        this.x = Integer.parseInt(params.getFirst());
        this.y = Integer.parseInt(params.get(1));
        this.codeType = params.get(2);
        this.height = Integer.parseInt(params.get(3));
        this.humanReadable = Integer.parseInt(params.get(4));
        this.rotation = Integer.parseInt(params.get(5));
        this.narrow = Integer.parseInt(params.get(6));
        this.wide = Integer.parseInt(params.get(7));
        this.content = params.get(8).replace("\"", "");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getCodeType() {
        return codeType;
    }

    public int getHeight() {
        return height;
    }

    public int getHumanReadable() {
        return humanReadable;
    }

    public int getRotation() {
        return rotation;
    }

    public int getNarrow() {
        return narrow;
    }

    public int getWide() {
        return wide;
    }

    public String getContent() {
        return content;
    }
}
