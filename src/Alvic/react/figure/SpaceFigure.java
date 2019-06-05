package Alvic.react.figure;

import processing.core.PVector;

public class SpaceFigure implements Figure {
    private boolean space;

    public SpaceFigure(boolean space) {
        this.space = space;
    }

    @Override
    public PVector[] getOuterVector() {
        return new PVector[0];
    }

    @Override
    public PVector[] getInterVector() {
        return new PVector[0];
    }

    @Override
    public boolean intersect(Figure that) {
        return that instanceof SpaceFigure ? space && ((SpaceFigure)that).space : space;
    }
}
