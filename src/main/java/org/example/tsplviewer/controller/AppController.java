package org.example.tsplviewer.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.example.tsplviewer.model.TSPLAnalysisResult;
import org.example.tsplviewer.service.TSPLAnalysisService;
import org.example.tsplviewer.model.command.TSPLCommand;
import org.example.tsplviewer.model.ValidationError;
import org.example.tsplviewer.renderer.LabelElementRenderer;
import org.example.tsplviewer.renderer.LabelPreview;

import java.util.List;
import java.util.stream.Collectors;

public class AppController {

    @FXML private TextArea tsplTextArea;
    @FXML private Canvas previewCanvas;
    @FXML private TextArea validationArea;
    @FXML private GridPane settingsGrid;
    @FXML private GridPane variableGrid;

    private final TSPLAnalysisService analysisService = new TSPLAnalysisService();
    private final LabelPreview labelPreview = new LabelPreview();
    private final LabelElementRenderer elementRenderer = new LabelElementRenderer();

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
            displayVariables(result.codeCommands(), result.drawCommands());
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

    private void displayVariables(List<TSPLCommand> codeCommands, List<TSPLCommand> drawCommands) {
        variableGrid.getChildren().clear();

        for (int i = 0; i < codeCommands.size(); i++) {
            String prompt = codeCommands.get(i).getParams().getFirst().replaceAll("^\"|\"$", "");
            String variableName = codeCommands.get(i).getParams().get(1);
            addVariableRow(prompt, variableName, i, drawCommands);
        }
    }

    private void addVariableRow(String prompt, String variableName, int row, List<TSPLCommand> drawCommands) {
        if (prompt.isEmpty() || variableName.isEmpty()) return;

        Label label = new Label(prompt);
        variableGrid.add(label, 0, row);

        TextField input = new TextField();
        input.setPrefWidth(150);
        variableGrid.add(input, 1, row);

        // add hasmap for variables to keep updating the value

        input.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (wasFocused && !isNowFocused) {
                String newValue = input.getText();
                updatePreview(variableName, newValue, drawCommands);
            }
        });
    }

    private void updatePreview(String variableName, String newValue, List<TSPLCommand> drawCommands) {
        for (TSPLCommand cmd : drawCommands) {
            List<String> params = cmd.getParams();

            if (params.getLast().equals(variableName.trim())) {
                params.set(params.size() - 1, newValue);
            }
//            System.out.println(params);
        }
        System.out.println(drawCommands);
        redrawLabelPreview(drawCommands);
    }

    private void redrawLabelPreview(List<TSPLCommand> commands) {
        GraphicsContext gc = previewCanvas.getGraphicsContext2D();
        gc.clearRect(0,0, previewCanvas.getWidth(), previewCanvas.getHeight());
        labelPreview.render(commands, gc);
    }
}
