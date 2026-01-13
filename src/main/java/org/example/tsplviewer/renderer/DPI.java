package org.example.tsplviewer.renderer;

public final class DPI {

    public static final double PRINTER_DPI = 300.0;
    public static final double SCREEN_DPI = 96.0;

    private DPI() {}

    public static double d2p(int dots) {
        return dots * (SCREEN_DPI / PRINTER_DPI);
    }
}
