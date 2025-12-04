package org.example.tsplviewer.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextArea;
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

    private TSPLParser tsplParser;
    private LabelPreview labelPreview;

    public void initialize() {
        tsplTextArea.textProperty().addListener((obs, oldText, newText) -> {
            List<TSPLCommand> commands = tsplParser.parse(newText);
            List<String> errors = tsplParser.validate(newText);

            previewCanvas = labelPreview.render(commands);

            validationArea.setText(String.join("\n", errors));
        });
    }
}
