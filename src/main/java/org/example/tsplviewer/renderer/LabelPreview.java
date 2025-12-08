package org.example.tsplviewer.renderer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.example.tsplviewer.model.TSPLCommand;
import org.example.tsplviewer.model.TextCommand;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class LabelPreview {

    private static final double PRINTER_DPI = 300.0;
    private static final double SCREEN_DPI = 96.0;

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
        double oneInchInMm = 25.4;

        double widthPx = labelWidth * SCREEN_DPI / oneInchInMm;
        double heightPx = labelHeight * SCREEN_DPI / oneInchInMm;

        gc.setFill(Color.WHITE);
        gc.fillRect(0,0, widthPx, heightPx);

        gc.setStroke(Color.BLACK);
        gc.strokeRect(0,0,widthPx, heightPx);

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
        for (TSPLCommand command : commands) {
            if (command instanceof TextCommand text) {
                double x = d2p(text.getX());
                double y = d2p(text.getY());

//                double fontSize = d2p(text.getFont())

                gc.setFill(Color.BLACK);
                gc.fillText(text.getContent(), x, y);
            }
        }
    }

    private double d2p(int dots) {
        return dots * (SCREEN_DPI / PRINTER_DPI);
    }
}
