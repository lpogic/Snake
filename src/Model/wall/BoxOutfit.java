package Model.wall;

import Alvic.Apk;

public class BoxOutfit {

    private float width;
    private float height;
    private float x;
    private float y;
    private int color;
    private int strokeColor;
    private float strokeWidth;

    public BoxOutfit(float width, float height, float x, float y, int color) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.color = color;
        this.strokeColor = 0;
        this.strokeWidth = 0;
    }

    public void print(){
        Apk.applet.fill(color);
        if(strokeWidth > 0.0f) {
            Apk.applet.strokeWeight(strokeWidth);
            Apk.applet.stroke(strokeColor);
        } else Apk.applet.noStroke();
        Apk.applet.rect(x, y, width, height);
    }
}
