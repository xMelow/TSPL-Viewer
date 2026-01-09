package org.example.tsplviewer.parser;

import org.example.tsplviewer.model.CommandType;
import org.example.tsplviewer.model.TSPLCommand;
import org.example.tsplviewer.model.codeCommands.InputCommand;
import org.example.tsplviewer.model.drawCommands.*;
import org.example.tsplviewer.model.printCommands.*;

import java.util.List;

public class CommandFactory {

    public TSPLCommand create(String name, List<String> params) {
        return switch (name.toUpperCase()) {
            case "TEXT" -> new TextCommand(name, params, CommandType.DRAW);
            case "BOX" -> new BoxCommand(name, params, CommandType.DRAW);
            case "BAR" -> new BarCommand(name, params, CommandType.DRAW);
            case "CIRCLE" -> new CircleCommand(name, params, CommandType.DRAW);
            case "QRCODE" -> new QRCodeCommand(name, params, CommandType.DRAW);
            case "BARCODE" -> new BarcodeCommand(name, params, CommandType.DRAW);
            case "BLOCK" -> new BlockCommand(name, params, CommandType.DRAW);
            case "SIZE" -> new SizeCommand(name, params, CommandType.DRAW);

            case "DENSITY" -> new DensityCommand(name, params, CommandType.SETTING);
            case "GAP" -> new GapCommand(name, params, CommandType.SETTING);
            case "SPEED" -> new SpeedCommand(name, params, CommandType.SETTING);
            case "DIRECTION" -> new DirectionCommand(name, params, CommandType.SETTING);
            case "SHIFT" -> new ShiftCommand(name, params, CommandType.SETTING);
            case "OFFSET" -> new OffsetCommand(name, params, CommandType.SETTING);
            case "REFERENCE" -> new ReferenceCommand(name, params, CommandType.SETTING);

            case "INPUT" -> new InputCommand(name, params, CommandType.CODE);
            default -> new TSPLCommand(name, params, CommandType.UNKNOWN);
        };
    }
}
