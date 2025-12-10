package org.example.tsplviewer.model.commands;

import org.example.tsplviewer.model.TSPLCommand;

import java.util.List;

public class QRCodeCommand extends TSPLCommand {

    private int x;
    private int y;
    private String eccLevel;
    private int cellWidth;
    private String mode;
    private int rotation;
    private String content;

    public QRCodeCommand(String name, List<String> params) {
        super(name, params);

        this.x = Integer.parseInt(params.getFirst());
        this.y = Integer.parseInt(params.get(1));
        this.eccLevel = params.get(2);
        this.cellWidth = Integer.parseInt(params.get(3));
        this.mode = params.get(4);
        this.rotation = Integer.parseInt(params.get(5));
        this.content = params.get(8).replace("\"", "");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getEccLevel() {
        return eccLevel;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public String getMode() {
        return mode;
    }

    public int getRotation() {
        return rotation;
    }

    public String getContent() {
        return content;
    }
}
