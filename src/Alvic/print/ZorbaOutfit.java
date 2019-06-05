package Alvic.print;

import Alvic.Apk;
import Alvic.react.body.RigidBody;

public class ZorbaOutfit extends RigidBodyOutfit{

    public static final float NO_STROKE = 0f;

    private int color;
    private int strokeColor;
    private float strokeWidth;

    public ZorbaOutfit(RigidBody body, int color) {
        super(body);
        this.color = color;
        this.strokeWidth = NO_STROKE;
    }

    public ZorbaOutfit(RigidBody body, int color, int strokeColor, float strokeWidth) {
        this(body, color);
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    @Override
    public void print() {
        Apk.applet.fill(color);
        if(strokeWidth > 0.0f) {
          Apk.applet.strokeWeight(strokeWidth);
          Apk.applet.stroke(strokeColor);
        } else Apk.applet.noStroke();
        float diameter = getBody().getWrapper().getRadius() * 2;
        Apk.applet.ellipse(getBody().getPosition().x,getBody().getPosition().y, diameter, diameter);
    }
}
