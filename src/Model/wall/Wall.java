package Model.wall;

import Alvic.print.RigidBodyOutfit;
import Alvic.print.Visible;
import Alvic.print.QuadBodyOutfit;
import Alvic.react.Carnal;
import Alvic.react.body.RelativisticBody;
import Alvic.react.body.RigidBody;
import Alvic.react.figure.FigureFactory;
import Model.snake.Snake;

import java.util.List;

public class Wall implements Visible, Carnal {

    public static final Object wallDimension = new Object();

    private RigidBody body;
    private RigidBodyOutfit outfit;

    public Wall(float w, float h, float x, float y) {
        body = new RigidBody(this, x, y, FigureFactory.makeRectangle(w, h),
                (float)Math.sqrt(w * w + h * h) / 2);
        outfit = new QuadBodyOutfit(body, 0xFFE49739, 0, QuadBodyOutfit.NO_STROKE);
        body.rotate(1.0f);
    }

    public Wall(RigidBody body, RigidBodyOutfit outfit) {
        body.setOwner(this);
        this.body = body;
        outfit.setBody(body);
        this.outfit = outfit;
    }

    @Override
    public void print() {
        outfit.print();
    }

    @Override
    public void loadBody(List<RelativisticBody> collector) {
        collector.add(RelativisticBody.builder(body).addDwell(wallDimension).addAffect(Snake.headDimension).get());
    }
}
