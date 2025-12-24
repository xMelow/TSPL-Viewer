package org.example.tsplviewer.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.example.tsplviewer.model.PrinterSettings;
import org.example.tsplviewer.model.TSPLCommand;
import org.example.tsplviewer.parser.TSPLParser;
import org.example.tsplviewer.renderer.LabelPreview;
import java.util.List;

public class AppController {

    @FXML
    private TextArea tsplTextArea;

    @FXML
    private Canvas previewCanvas;

    @FXML
    private TextArea validationArea;

    @FXML
    private GridPane settingsGrid;

    private final TSPLParser tsplParser;
    private final LabelPreview labelPreview;

    public AppController() {
        tsplParser = new TSPLParser();
        labelPreview = new LabelPreview();
    }

    public void initialize() {
        tsplTextArea.textProperty().addListener((obs, oldText, newText) -> {
            List<TSPLCommand> commands = tsplParser.parse(newText);
            List<String> errors = tsplParser.validate(newText);

            drawLabelPreview(commands);
            PrinterSettings settings = getLabelPrintSettings(commands);
            displaySettings(settings);

            validationArea.setText(String.join("\n", errors));
        });
    }

    private void drawLabelPreview(List<TSPLCommand> commands) {
        GraphicsContext gc = previewCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, previewCanvas.getWidth(), previewCanvas.getHeight());
        gc.setLineWidth(1);
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.BLACK);
        labelPreview.render(commands, gc);
    }

    private PrinterSettings getLabelPrintSettings(List<TSPLCommand> commands) {
        PrinterSettings settings = new PrinterSettings();
        for (TSPLCommand cmd : commands) {
            switch (cmd.getName().toUpperCase()) {
                case "SIZE" -> settings.setSize(cmd.getParams());
                case "GAP" -> settings.setGap(cmd.getParams());
                case "DENSITY" -> settings.setDensity(cmd.getParams());
                case "SPEED" -> settings.setSpeed(cmd.getParams());
//                case "DIRECTION" -> settings.setDirection(cmd.getParams());
            }
        }
        return settings;
    }

    private void displaySettings(PrinterSettings settings) {
        // show the settings in on the javafx screen
        settingsGrid.getChildren().clear();

        addSettingsRow("Size", settings.getSize().toString(), 0);
        addSettingsRow("Gap", settings.getGap().toString(), 1);
        addSettingsRow("Density", String.valueOf(settings.getDensity()), 2);
        addSettingsRow("Speed", String.valueOf(settings.getSpeed()), 3);
    }

    private void addSettingsRow(String name, String value, int row) {
        Label label = new Label(name);
        TextField textField = new TextField(value);
        settingsGrid.add(label, 0, row);
        settingsGrid.add(textField, 1, row);
    }
}
