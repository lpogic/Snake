package Model.wall;

import Alvic.print.Visible;
import Alvic.react.Carnal;
import Alvic.react.body.RelativisticBody;
import Alvic.react.body.RigidBody;
import Alvic.react.figure.FigureFactory;
import Model.snake.Snake;

import java.util.List;

public class Box implements Visible, Carnal {

    private RigidBody body;
    private BoxOutfit outfit;

    public Box(float w, float h, float x, float y) {
        body = new RigidBody(this, x, y, FigureFactory.makeAntiRectangle(w, h),
                -Math.min(w, h) / 2);
        outfit = new BoxOutfit(w, h, x, y, 0xFF214C25);
    }


    @Override
    public void print() {
        outfit.print();
    }

    @Override
    public void loadBody(List<RelativisticBody> collector) {
        collector.add(RelativisticBody.builder(body).addDwell(Wall.wallDimension).addAffect(Snake.headDimension).get());
    }
}
