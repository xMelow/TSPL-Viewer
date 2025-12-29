package org.example.tsplviewer.parser;

import org.example.tsplviewer.model.TSPLCommand;
import org.example.tsplviewer.model.commands.*;

import java.util.List;

public class CommandFactory {

    public TSPLCommand create(String name, List<String> params) {
        return switch (name.toUpperCase()) {
            case "TEXT" -> new TextCommand(name, params);
            case "BOX" -> new BoxCommand(name, params);
            case "BAR" -> new BarCommand(name, params);
            case "CIRCLE" -> new CircleCommand(name, params);
            case "QRCODE" -> new QRCodeCommand(name, params);
            case "BARCODE" -> new BarcodeCommand(name, params);
            case "BLOCK" -> new BlockCommand(name, params);
            case "SIZE" -> new SizeCommand(name, params);
            case "INPUT" -> new InputCommand(name, params);
            default -> new TSPLCommand(name, params);
        };
    }
}
