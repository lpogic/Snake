package Model.food;

import Alvic.print.RigidBodyOutfit;
import Alvic.print.ZorbaOutfit;
import Alvic.react.Carnal;
import Alvic.react.body.RelativisticBody;
import Alvic.react.body.RigidBody;
import Alvic.react.figure.SpaceFigure;
import Alvic.print.Visible;
import Model.effect.Effect;
import Model.snake.Snake;
import processing.core.PVector;

import java.util.Collection;
import java.util.List;

public abstract class Food implements Visible, Carnal {

    private FoodSupplier owner;
    private RigidBody body;
    private RigidBodyOutfit outfit;

    public Food(FoodSupplier owner) {
        this.owner = owner;
    }

    public Food(FoodSupplier owner, RigidBody body, RigidBodyOutfit outfit) {
        this.owner = owner;
        body.setOwner(this);
        this.body = body;
        outfit.setBody(body);
        this.outfit = outfit;
    }

    public void setPosition(PVector newPosition){
        body.setPosition(newPosition);
    }

    public RigidBody getBody() {
        return body;
    }

    public RigidBodyOutfit getOutfit() {
        return outfit;
    }

    public void setBody(RigidBody body) {
        body.setOwner(this);
        this.body = body;
    }

    public void setOutfit(RigidBodyOutfit outfit) {
        outfit.setBody(body);
        this.outfit = outfit;
    }

    public void consumed(){
        owner.reportConsumption(this);
    }

    public void loadEffect(Collection<Effect> collector){

    }

    @Override
    public void loadBody(List<RelativisticBody> collector) {
        collector.add(new RelativisticBody.Builder(body).addAffect(Snake.headDimension).get());
    }

    @Override
    public void print() {
        outfit.print();
    }

    @Override
    public String toString() {
        return "Food";
    }
}
