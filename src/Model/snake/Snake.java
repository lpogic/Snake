package Model.snake;

import Alvic.control.Controller;
import Alvic.react.Carnal;
import Alvic.react.body.RelativisticBody;
import Alvic.print.Visible;
import Alvic.update.Alive;
import Model.effect.Effect;
import processing.core.PVector;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Snake implements Visible, Alive, Carnal {

    public static final Object headDimension = new Object();

    private SnakeBody body;
    private SnakeOutfit outfit;
    private SnakeParameters parameters;
    private Set<Effect> effects;
    private Controller ctrl;

    public Snake(Controller ctrl, float headX, float headY, float momentumX, float momentumY) {
        body = new SnakeBody(this,headX, headY, new PVector(momentumX, momentumY), 5);
        outfit = new SnakeOutfit(body);
        parameters = new SnakeParameters(this,4f,0.01f, 0, 0);
        effects = new HashSet<>();
        this.ctrl = ctrl;
    }

    @Override
    public void loadBody(List<RelativisticBody> list) {
        body.loadBody(list);
    }

    @Override
    public void print() {
        outfit.print();
    }

    @Override
    public void update() {
        parameters.reset();
        Set<Effect> survivedEffects = new HashSet<>();
        for(Effect it : effects){
            it.apply(this);
            if(it.live())survivedEffects.add(it);
        }
        effects = survivedEffects;
        parameters.load();
        if(ctrl.induced(1)){
            body.turnLeft();
        }
        else if(ctrl.induced(2)){
            body.turnRight();
        }
        body.move();
    }

    public SnakeBody getBody() {
        return body;
    }

    public SnakeOutfit getOutfit() {
        return outfit;
    }

    public SnakeParameters getParameters() {
        return parameters;
    }

    public Set<Effect> getEffects() {
        return effects;
    }

    @Override
    public String toString() {
        return "Snake";
    }
}
