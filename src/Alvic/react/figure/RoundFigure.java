package Alvic.react.figure;

import processing.core.PVector;

public class RoundFigure implements Figure {

    private PVector offset;
    private float radius;

    public RoundFigure(PVector offset, float radius){
        this.offset = offset;
        this.radius = radius;
    }

    @Override
    public PVector[] getOuterVector() {
        return new PVector[]{offset};
    }

    @Override
    public PVector[] getInterVector() {
        return new PVector[0];
    }

    public PVector getOffset() {
        return offset;
    }

    public void setOffset(PVector offset) {
        this.offset = offset;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public boolean intersect(Figure figure){
        if(figure == null)return false;
        if(figure instanceof RoundFigure) return intersect((RoundFigure) figure);
        return figure.intersect(this);
    }

    private boolean intersect(RoundFigure that){
        if(this.radius > 0 && that.radius > 0) {
            float radSum = this.radius + that.radius;
            return radSum * radSum > PVector.sub(offset, that.offset).magSq();
        }
        if(this.radius <= 0 && that.radius <= 0) return true;
        float radSum = this.radius + that.radius;
        return radSum * radSum < PVector.sub(offset, that.offset).magSq();
    }
}
