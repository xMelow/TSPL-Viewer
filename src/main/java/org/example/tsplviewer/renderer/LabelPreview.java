package org.example.tsplviewer.renderer;

import com.google.zxing.EncodeHintType;
import com.google.zxing.oned.Code128Writer;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.example.tsplviewer.model.*;
import org.example.tsplviewer.model.drawCommands.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.common.BitMatrix;
import org.example.tsplviewer.model.printCommands.SizeCommand;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static org.example.tsplviewer.renderer.DPI.SCREEN_DPI;

public class LabelPreview {

    private final LabelElementRenderer elementRenderer = new LabelElementRenderer();

    public LabelPreview() {}

    public void render(List<TSPLCommand> commands, GraphicsContext gc) {
        SizeCommand size = getSizeCommand(commands);
        if (size == null) return;

        double oneInchInMm = 25.4;
        double labelWidthPx = size.getWidth() * SCREEN_DPI / oneInchInMm;
        double labelHeightPx = size.getHeight() * SCREEN_DPI / oneInchInMm;

        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        double offsetX = (canvasWidth - labelWidthPx) / 2;
        double offsetY = (canvasHeight - labelHeightPx) / 2;

        gc.save();
        gc.translate(offsetX, offsetY);

        drawLabelFormat(gc, labelWidthPx, labelHeightPx);
        elementRenderer.render(gc, commands);

        gc.restore();
    }

    private void drawLabelFormat(GraphicsContext gc, double labelWidth, double labelHeight) {
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0, labelWidth, labelHeight);

        gc.setStroke(Color.BLACK);
        gc.strokeRect(0,0,labelWidth, labelHeight);
    }

    private SizeCommand getSizeCommand(List<TSPLCommand> commands) {
        return commands.stream()
                .filter(c -> c instanceof SizeCommand)
                .map(c -> (SizeCommand) c)
                .findFirst()
                .orElse(null);
    }
}
