package org.example.tsplviewer.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
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
}
