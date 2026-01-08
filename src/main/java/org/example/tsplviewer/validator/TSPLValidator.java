package org.example.tsplviewer.validator;

import org.example.tsplviewer.model.TSPLCommand;
import org.example.tsplviewer.model.ValidationError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TSPLValidator {

    // check empty name + correct names
    // check brackets and comma's --> parser?
    // check parameters
    // check position on label

    private static final List<String> VALID_TSPL_COMMANDS = Arrays.asList(
            "SIZE", "GAP", "REFERENCE", "SPEED", "DENSITY", "RIBBON", "PEEL", "CUTTER", "PARTIAL_CUTTER", "TEAR", "REWIND", "DIRECTION", "SHIFT", "OFFSET", "PRINT",
            "CLS", "TEXT", "BLOCK", "BARCODE", "BAR", "CIRCLE", "QRCODE", "BOX", "SIZE",
            "COUNTER", "INPUT", "CODESPACE"
    );

    public List<ValidationError> validate(List<TSPLCommand> commands) {
        List<ValidationError> errors = new ArrayList<>();

        for (int i = 0; i < commands.size(); i++) {
            TSPLCommand command = commands.get(i);
            int line = i + 1;

            errors.addAll(validateName(command, line));
            errors.addAll(validateParams(command, line));
        }
        return errors;
    }

    private List<ValidationError> validateName(TSPLCommand cmd, int line) {
        List<ValidationError> errors = new ArrayList<>();

        if (cmd.getName().isEmpty()) {
            errors.add(new ValidationError(line, "Empty command name"));
            return errors;
        }

        if (!VALID_TSPL_COMMANDS.contains(cmd.getName())) {
            errors.add(new ValidationError(line, "No known command: " + cmd.getName()));
        }
        return errors;
    }

    private List<ValidationError> validateParams(TSPLCommand cmd, int line) {
        List<ValidationError> errors = new ArrayList<>();

        int paramCount = cmd.getParams().size();

        if (paramCount < cmd.minParams()) {
            errors.add(new ValidationError(line, cmd.getName() + " requires minimal of " + cmd.minParams() + " parameters"));
        }

        if (paramCount > cmd.maxParams()) {
            errors.add(new ValidationError(line, cmd.getName() + " has a maximum of " + cmd.maxParams() + " parameters"));
        }
        return errors;
    }
}
