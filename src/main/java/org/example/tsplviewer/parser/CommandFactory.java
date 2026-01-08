package org.example.tsplviewer.parser;

import org.example.tsplviewer.model.TSPLCommand;
import org.example.tsplviewer.model.codeCommands.InputCommand;
import org.example.tsplviewer.model.drawCommands.*;
import org.example.tsplviewer.model.printCommands.DensityCommand;
import org.example.tsplviewer.model.printCommands.GapCommand;
import org.example.tsplviewer.model.printCommands.SizeCommand;
import org.example.tsplviewer.model.printCommands.SpeedCommand;

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
            case "DENSITY" -> new DensityCommand(name, params);
            case "GAP" -> new GapCommand(name, params);
            case "SPEED" -> new SpeedCommand(name, params);

            case "INPUT" -> new InputCommand(name, params);
            default -> new TSPLCommand(name, params);
        };
    }
}
