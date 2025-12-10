package org.example.tsplviewer.renderer;

import com.google.zxing.EncodeHintType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.example.tsplviewer.model.*;
import org.example.tsplviewer.model.commands.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.common.BitMatrix;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
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
            switch (command) {
                case TextCommand text -> drawTextElement(gc, text);
                case BoxCommand box -> drawBoxElement(gc, box);
                case BarCommand bar -> drawBarElement(gc, bar);
                case CircleCommand circle -> drawCircleElement(gc, circle);
                case QRCodeCommand qr -> drawQrElement(gc, qr);
                default -> { //do nothing yet
                }
            }
        }
    }

    private void drawTextElement(GraphicsContext gc, TextCommand text) {
        double x = d2p(text.getX());
        double y = d2p(text.getY());
        double baseDotHeight = 3.6;
        double fontSize = d2p((int) (baseDotHeight * text.getyMultiplication()));

        gc.setFont(Font.font("Arial", fontSize));
        gc.setFill(Color.BLACK);

        Text tempText = new Text(text.getContent());
        tempText.setFont(Font.font("Arial", fontSize));
        double ascent = tempText.getLayoutBounds().getHeight();
        double yBaseline = y + ascent;

        gc.fillText(text.getContent(), x, yBaseline);
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

    private void drawQrElement(GraphicsContext gc, QRCodeCommand qr) {
        try {
            ErrorCorrectionLevel ecc = switch (qr.getEccLevel()) {
                case "L" -> ErrorCorrectionLevel.L;
                case "Q" -> ErrorCorrectionLevel.Q;
                case "H" -> ErrorCorrectionLevel.H;
                default -> ErrorCorrectionLevel.M;
            };

            Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.MARGIN, 0);
            hints.put(EncodeHintType.ERROR_CORRECTION, ecc);

            QRCodeWriter qrWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrWriter.encode(
                    qr.getContent(),
                    BarcodeFormat.QR_CODE,
                    0,0,
                    hints
            );

            double startX = d2p(qr.getX());
            double startY = d2p(qr.getY());
            double cellSize = d2p(qr.getCellWidth());

            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();

            gc.setFill(Color.BLACK);
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    if (bitMatrix.get(col, row)) {
                        double px = startX + col * cellSize;
                        double yx = startY + row * cellSize;
                        gc.fillRect(px, yx, cellSize, cellSize);
                    }
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private double d2p(int dots) {
        return dots * (SCREEN_DPI / PRINTER_DPI);
    }
}
