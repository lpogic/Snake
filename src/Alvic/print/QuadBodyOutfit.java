package Alvic.print;

import Alvic.Apk;
import Alvic.react.body.RigidBody;
import processing.core.PShape;
import processing.core.PVector;

public class QuadBodyOutfit extends RigidBodyOutfit {

    public static final float NO_STROKE = 0f;

    private PShape shape;

    public QuadBodyOutfit(RigidBody body) {
        this(body, 255, 0, NO_STROKE);
    }

    public QuadBodyOutfit(RigidBody body, int color, int strokeColor, float strokeWidth) {
        super(body);
        shape = Apk.applet.createShape();
        shape.beginShape();
        shape.fill(color);
        if(strokeWidth > 0.0f) {
            shape.strokeWeight(strokeWidth);
            shape.stroke(strokeColor);
        } else shape.noStroke();

        PVector current = new PVector();
        current.set(body.getFigure().getOuterVector()[0]);
        PVector[] inter = body.getFigure().getInterVector();
        for(PVector it : inter){
            current.add(it);
            shape.vertex(current.x, current.y);
        }
        shape.endShape();
    }

    public int getColor() {
        return shape.getFill(0);
    }

    public void setColor(int color) {
        shape.setFill(color);
    }

    public int getStrokeColor() {
        return shape.getStroke(0);
    }

    public void setStrokeColor(int strokeColor) {
        shape.setStroke(strokeColor);
    }

    public float getStrokeWidth() {
        return shape.getStrokeWeight(0);
    }

    public void setStrokeWidth(float strokeWidth) {
        shape.setStrokeWeight(strokeWidth);
    }

    @Override
    public void print() {
        shape.resetMatrix();
        shape.rotate(getBody().getRotation());
        Apk.applet.shape(shape, getBody().getPosition().x, getBody().getPosition().y);
    }
}
