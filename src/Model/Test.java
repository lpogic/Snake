package Model;

import Alvic.Apk;
import Alvic.control.MouseController;
import Alvic.print.Visible;
import Alvic.react.Carnal;
import Alvic.react.body.RelativisticBody;
import Alvic.react.body.RigidBody;
import Alvic.react.figure.RoundFigure;
import Alvic.update.Alive;
import Model.wall.Wall;
import processing.core.PVector;

import java.util.List;

public class Test implements Alive, Visible, Carnal {

    private RigidBody body;

    public Test() {
        body = new RigidBody(this,0,0,
                new RoundFigure(new PVector(0,0),15),15);
    }

    @Override
    public void loadBody(List<RelativisticBody> collector) {
        collector.add(new RelativisticBody.Builder(body).addAffect(Wall.wallDimension).get());
    }

    @Override
    public void print() {
        Apk.applet.fill(0);
        Apk.applet.ellipse(body.getPosition().x,body.getPosition().y,30,30);
    }


    @Override
    public void update() {
        if(body.collide()){
            System.out.println("c");
            body.clearCollided();
        }
        body.setPosition(new PVector(Apk.applet.mouseX, Apk.applet.mouseY));
    }
}