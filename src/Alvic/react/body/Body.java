package Alvic.react.body;

import Alvic.react.Carnal;

public interface Body {
    boolean collide(Body body);
    void collision(Body body);
    Carnal getOwner();
}
