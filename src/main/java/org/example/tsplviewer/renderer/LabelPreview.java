package org.example.tsplviewer.renderer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.tsplviewer.model.*;

import java.util.List;
import java.util.Objects;

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
                drawTextElement(gc, text);
            }
            if (command instanceof BoxCommand box) {
                drawBoxElement(gc, box);
            }
            if (command instanceof BarCommand bar) {
                drawBarElement(gc, bar);
            }
            if (command instanceof CircleCommand circle) {
                drawCircleElement(gc, circle);
            }
        }
    }

    private void drawTextElement(GraphicsContext gc, TextCommand text) {
        double x = d2p(text.getX());
        double y = d2p(text.getY());

//                double fontSize = d2p(text.getFont())

        gc.setFill(Color.BLACK);
        gc.fillText(text.getContent(), x, y);
    }

    private void drawBoxElement(GraphicsContext gc, BoxCommand box) {
        double x = d2p(box.getX());
        double y = d2p(box.getY());
        double xEnd = d2p(box.getxEnd());
        double yEnd = d2p(box.getyEnd());
        double width = xEnd - x;
        double height = yEnd - y;

        gc.setStroke(Color.BLACK);
        gc.strokeRect(x, y, width, height);

    }

    private void drawBarElement(GraphicsContext gc, BarCommand bar) {
        double x = d2p(bar.getX());
        double y = d2p(bar.getY());
        double width = d2p(bar.getWidth());
        double height = d2p(bar.getHeight());

        gc.setStroke(Color.BLACK);
        gc.fillRect(x, y, width, height);
    }

    private void drawCircleElement(GraphicsContext gc, CircleCommand circle) {
        double x = d2p(circle.getxStart());
        double y = d2p(circle.getyStart());
        double d = d2p(circle.getDiameter());
        double thickness = d2p(circle.getThickness());

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(thickness);
        gc.strokeOval(x, y, d, d);
    }

    private double d2p(int dots) {
        return dots * (SCREEN_DPI / PRINTER_DPI);
    }
}
