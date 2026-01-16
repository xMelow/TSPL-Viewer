package org.example.tsplviewer.renderer;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.example.tsplviewer.model.command.TSPLCommand;
import org.example.tsplviewer.model.command.draw.*;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static org.example.tsplviewer.renderer.DPI.*;

public class LabelElementRenderer {

    public void render(GraphicsContext gc, List<TSPLCommand> commands) {
        for (TSPLCommand command : commands) {
            switch (command) {
                case TextCommand text -> drawTextElement(gc, text);
                case BoxCommand box -> drawBoxElement(gc, box);
                case BarCommand bar -> drawBarElement(gc, bar);
                case CircleCommand circle -> drawCircleElement(gc, circle);
                case QRCodeCommand qr -> drawQrElement(gc, qr);
                case BarcodeCommand barcode -> drawBarcodeElement(gc, barcode);
                case BlockCommand block -> drawBlockElement(gc, block);
                case PutBMPCommand image -> drawBMPElement(gc, image);
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

    private void drawBarcodeElement(GraphicsContext gc, BarcodeCommand barcode) {
        try {
            double startX = d2p(barcode.getX());
            double startY = d2p(barcode.getY());
            double widePx = d2p(barcode.getWide());
            double narrowPx = d2p(barcode.getNarrow());
            double barHeight = d2p(barcode.getHeight());

            Code128Writer writer = new Code128Writer();

            BitMatrix matrix = writer.encode(
                    barcode.getContent(),
                    BarcodeFormat.CODE_128,
                    0,
                    (int) barHeight
            );
            gc.save();
            gc.setFill(Color.BLACK);

            int matrixWidth = matrix.getWidth();
            double xCursor = startX;

            for (int x = 0; x < matrixWidth; ) {

                boolean isBar = matrix.get(x, 0);
                int runLength = 1;

                while (x + runLength < matrixWidth && matrix.get(x + runLength, 0) == isBar) {
                    runLength++;
                }

                double drawWidth = (runLength == 3) ? widePx : narrowPx;

                if (isBar) {
                    gc.fillRect(xCursor, startY, drawWidth, barHeight);
                }

                xCursor += drawWidth;
                x += runLength;
            }

            gc.restore();

            if (barcode.getHumanReadable() != 0) {
                gc.setFill(Color.BLACK);
                gc.setFont(Font.font("Arial", barHeight / 4));

                Text t = new Text(barcode.getContent());
                t.setFont(gc.getFont());
                double textWidth = t.getBoundsInLocal().getWidth();

                double barcodeWidth = xCursor - startX;
                double textX = startX + (barcodeWidth - textWidth) / 2;
                double textY = startY + barHeight + barHeight / 4;

                gc.fillText(barcode.getContent(), textX, textY);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawBlockElement(GraphicsContext gc, BlockCommand block) {
        double x = d2p(block.getX());
        double y = d2p(block.getY());
        double width = d2p(block.getWidth());
        double height = d2p(block.getHeight());

        double baseDotHeight = 3.6;
        double fontSize = d2p((int) (baseDotHeight * block.getyMultiplication()));

        gc.setFont(Font.font("Arial", fontSize));

        gc.save();

//         green outline for box size
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(1);
        gc.strokeRect(x, y, width, height);

        gc.setFill(Color.BLACK);
        gc.setTextBaseline(VPos.TOP);

        String[] lines = block.getContent().split("\n");
        double lineHeight = fontSize * 1.2;
        double yCursor = y;

        for (String line: lines) {
            double xCursor = x;
            
            if (block.getAlign() == 2) {
                Text temp = new Text(line);
                temp.setFont(gc.getFont());
                double textWidth = temp.getBoundsInLocal().getWidth();
                xCursor = x + (width - textWidth) / 2;
            }

            gc.fillText(line, xCursor, yCursor);
            yCursor += lineHeight;

            if (yCursor - y > height) break;
        }
        gc.restore();
    }

    private void drawBMPElement(GraphicsContext gc, PutBMPCommand bmp) {
        double x = d2p(bmp.getX());
        double y = d2p(bmp.getY());

        try {
            Image image = new Image(
                    "file:" + bmp.getFilename(),
                    false
            );

            double scale = SCREEN_DPI / PRINTER_DPI;

            gc.drawImage(
                    image,
                    x,
                    y,
                    image.getWidth() * scale,
                    image.getHeight() * scale
            );

        } catch (Exception e) {
            System.err.println("Failed to load BMP: " + bmp.getFilename());
            e.printStackTrace();
        }
    }

}
