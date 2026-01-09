package org.example.tsplviewer.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.example.tsplviewer.model.TSPLAnalysisResult;
import org.example.tsplviewer.model.TSPLAnalysisService;
import org.example.tsplviewer.model.TSPLCommand;
import org.example.tsplviewer.model.ValidationError;
import org.example.tsplviewer.renderer.LabelPreview;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AppController {

    @FXML private TextArea tsplTextArea;
    @FXML private Canvas previewCanvas;
    @FXML private TextArea validationArea;
    @FXML private GridPane settingsGrid;

    private final TSPLAnalysisService analysisService = new TSPLAnalysisService();
    private final LabelPreview labelPreview = new LabelPreview();

    public AppController() {}

    public void initialize() {
        tsplTextArea.textProperty().addListener((obs, oldText, newText) -> {
            TSPLAnalysisResult result = analysisService.analyze(newText);

            validationArea.setText(result.errors().stream()
                    .map(ValidationError::toString)
                    .collect(Collectors.joining("\n"))
            );
            
            drawLabelPreview(result.drawCommands());
            displaySettings(result.settingsCommands());
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

    private void displaySettings(List<TSPLCommand> commands) {
        settingsGrid.getChildren().clear();

        for (int i = 0; i < commands.size(); i++) {
            addSettingsRow(commands.get(i).getName(), commands.get(i).getParams(), i);
        }

//        addSettingsRow("Size: ", settings.getSize(), 0);
//        addSettingsRow("Gap: ", settings.getGap(), 1);
//        addSettingsRow("Reference: ", settings.getReference(), 2);
//        addSettingsRow("Speed: ", Collections.singletonList((int) settings.getSpeed()), 3);
//        addSettingsRow("Density", settings.getDensity(), 4);
//        addSettingsRow("Direction", settings.getDirection(), 5);
//        addSettingsRow("Shift", settings.getShift(), 6);
//        addSettingsRow("Offset", Collections.singletonList(settings.getOffset()), 7);
    }

    private void addSettingsRow(String name, List<String> values, int row) {
        if (values == null || values.isEmpty()) return;

        Label label = new Label(name);
        settingsGrid.add(label, 0, row);

        for (int i = 0; i < values.size(); i++) {
            Label field = new Label(String.valueOf(values.get(i)));
            field.setPrefWidth(60);
            settingsGrid.add(field, i + 1, row);
        }
    }
}
