package org.example.tsplviewer.model;

public class ValidationError {

    private final int line;
    private final String message;

    public ValidationError(int line, String message) {
        this.line = line;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getLine() {
        return line;
    }

    @Override
    public String toString() {
        return "Line " + line + " : " + message;
    }
}
