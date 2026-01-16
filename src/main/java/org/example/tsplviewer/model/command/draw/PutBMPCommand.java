package org.example.tsplviewer.model.command.draw;

import org.example.tsplviewer.model.command.CommandType;
import org.example.tsplviewer.model.command.TSPLCommand;

import java.util.List;

public class PutBMPCommand extends TSPLCommand {

    private int x;
    private int y;
    private String filename;

    public PutBMPCommand(String name, List<String> params, CommandType type) {
        super(name, params, type);

        this.x = Integer.parseInt(params.getFirst());
        this.y = Integer.parseInt(params.get(1));
        this.filename = params.get(2).replace("\"", "");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getFilename() {
        return filename;
    }

    @Override
    public int minParams() {
        return 3;
    }

    @Override
    public int maxParams() {
        return 5;
    }
}
