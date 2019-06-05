package Alvic.utilities;

import processing.core.PApplet;
import processing.core.PVector;

public class PVectorUtil {

    public static float det(PVector a, PVector b){
        return a.x*b.y - a.y*b.x;
    }

    public static float dot(PVector a, PVector b){
        return a.x*b.x + a.y*b.y;
    }

    public static PVector rotationVector(float angle){return new PVector(PApplet.sin(angle),PApplet.cos(angle));}

    public static PVector randomPosition(float width, float height){
        return new PVector((float)(Math.random() * width),(float)(Math.random() * height));
    }
}
