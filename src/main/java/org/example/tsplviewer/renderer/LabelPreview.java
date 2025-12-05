package org.example.tsplviewer.renderer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.example.tsplviewer.model.TSPLCommand;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class LabelPreview {

    private int width = 400;
    private int height = 200;

    public LabelPreview() {

    }

    public void render(List<TSPLCommand> commands, GraphicsContext gc) {
        drawLabelFormat(gc, commands);
        drawLabelElements(gc, commands);
    }

    private void drawLabelFormat(GraphicsContext gc, List<TSPLCommand> commands) {
        double[] labelSize = getLabelWidthAndHeight(commands);
        double labelWidth = labelSize[0];
        double labelHeight = labelSize[1];
        double dpi = 96;

        double widthPx = labelWidth * dpi / 25.4;
        double heightPx = labelHeight * dpi / 25.4;

        gc.setFill(Color.WHITE);
        gc.fillRect(0,0, widthPx, heightPx);

        gc.setStroke(Color.BLACK);
        gc.strokeRect(0,0,widthPx, heightPx);

        gc.setFill(Color.BLACK);
    }

    private double[] getLabelWidthAndHeight(List<TSPLCommand> commands) {
        return commands.stream()
                .filter(c -> Objects.equals(c.getName(), "SIZE"))
                .findFirst()
                .map(c -> {
                    double width = Double.parseDouble(c.getParams().get(0));
                    double height = Double.parseDouble(c.getParams().get(1));
                    return new double[]{width, height};
                })
                .orElse(new double[]{400, 200});
    }

    private void drawLabelElements(GraphicsContext gc, List<TSPLCommand> commands) {

    }
}
