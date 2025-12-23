package org.example.tsplviewer.model;

import java.util.ArrayList;
import java.util.List;

public class PrinterSettings {

    private List<Integer> size;
    private List<Integer> gap;
    private int density;
    private int speed;
    private int direction;

    public PrinterSettings() {}

    private List<Integer> convertStringListToIntList(List<String> list) {
        List<Integer> result = new ArrayList<>();
        for (String param : list) {
            result.add(Integer.parseInt(param));
        }
        return result;
    }

    public void setSize(List<String> size) {
        this.size = convertStringListToIntList(size);
    }

    public void setGap(List<String> gap) {
        this.gap = convertStringListToIntList(gap);
    }

    public void setDensity(int density) {
        this.density = density;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public List<Integer> getSize() {
        return size;
    }

    public List<Integer> getGap() {
        return gap;
    }

    public int getDensity() {
        return density;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDirection() {
        return direction;
    }
}
