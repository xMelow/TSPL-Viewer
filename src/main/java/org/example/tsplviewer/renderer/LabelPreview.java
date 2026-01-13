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
        
        drawLabelFormat(gc, commands);
        elementRenderer.render(gc, commands);
    }

    private void drawLabelFormat(GraphicsContext gc, List<TSPLCommand> commands) {
        SizeCommand sizeCommand = getSizeCommand(commands);

        double oneInchInMm = 25.4;
        double widthPx = sizeCommand.getWidth() * SCREEN_DPI / oneInchInMm;
        double heightPx = sizeCommand.getHeight() * SCREEN_DPI / oneInchInMm;

        gc.setFill(Color.WHITE);
        gc.fillRect(0,0, widthPx, heightPx);

        gc.setStroke(Color.BLACK);
        gc.strokeRect(0,0,widthPx, heightPx);

    }

    private SizeCommand getSizeCommand(List<TSPLCommand> commands) {
        return commands.stream()
                .filter(c -> c instanceof SizeCommand)
                .map(c -> (SizeCommand) c)
                .findFirst()
                .orElse(null);
    }
}
