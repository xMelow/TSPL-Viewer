package org.example.tsplviewer.model;

import java.util.ArrayList;
import java.util.List;

public class PrinterSettings {

    private List<Integer> size;
    private List<Integer> gap;
    private List<Integer> density;
    private float speed;
    private List<Integer> direction;
    private List<Integer> shift;
    private int offset;
    private List<Integer> reference;

    public PrinterSettings() {}

    private List<Integer> convertStringListToIntList(List<String> list) {
        List<Integer> result = new ArrayList<>();
        for (String param : list) {
            result.add(Integer.parseInt(param));
        }
        return result;
    }

    public List<Integer> getShift() {
        return shift;
    }

    public void setShift(List<String> shift) {
        this.shift = convertStringListToIntList(shift);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(List<String> offset) {
        this.offset = Integer.parseInt(offset.getFirst());
    }

    public List<Integer> getReference() {
        return reference;
    }

    public void setReference(List<String> reference) {
        this.reference = convertStringListToIntList(reference);
    }

    public void setSize(List<String> size) {
        this.size = convertStringListToIntList(size);
    }

    public void setGap(List<String> gap) {
        this.gap = convertStringListToIntList(gap);
    }

    public void setDensity(List<String> density) {
        this.density = convertStringListToIntList(density);
    }

    public void setSpeed(List<String> speed) {
        this.speed = Float.parseFloat(speed.getFirst());
    }

    public void setDirection(List<String> direction) {
        this.direction = convertStringListToIntList(direction);
    }

    public List<Integer> getSize() {
        return size;
    }

    public List<Integer> getGap() {
        return gap;
    }

    public List<Integer> getDensity() {
        return density;
    }

    public float getSpeed() {
        return speed;
    }

    public List<Integer> getDirection() {
        return direction;
    }
}
