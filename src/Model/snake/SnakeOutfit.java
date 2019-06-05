package Model.snake;

import Alvic.Apk;
import Alvic.print.ZorbaOutfit;
import Alvic.react.body.RigidBody;
import Alvic.print.Visible;
import processing.core.PVector;

import java.util.Iterator;

public class SnakeOutfit implements Visible {

    private SnakeTail tail;
    private ZorbaOutfit headOutfit;
    private int segmentsColor;

    public SnakeOutfit() {
    }

    public SnakeOutfit(SnakeBody body) {
        this.tail = body.getTail();
        this.headOutfit = new ZorbaOutfit(body.getHead(), 0);
        segmentsColor = 0;
    }

    public void setSegmentsColor(int segmentsColor) {
        this.segmentsColor = segmentsColor;
    }

    public void setHeadColor(int headColor) {
        headOutfit.setColor(headColor);
    }

    @Override
    public void print() {
        PVector position;
        Iterator<RigidBody> it = tail.getSegments().descendingIterator();
        float size = 8;
        Apk.applet.noStroke();
        Apk.applet.fill(segmentsColor);
        while(it.hasNext()){
            position = it.next().getPosition();
            if(it.hasNext())
                Apk.applet.ellipse(position.x, position.y, size,size);
            else {
                Apk.applet.fill(headOutfit.getColor());
                Apk.applet.ellipse(position.x, position.y, size + 3, size + 3);
            }
            if(size < 18f)size+= 1f;
        }
        headOutfit.print();
    }
}
