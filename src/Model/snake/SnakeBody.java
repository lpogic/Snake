package Model.snake;

import Alvic.react.body.Body;
import Alvic.react.Carnal;
import Alvic.react.body.RelativisticBody;
import Alvic.react.body.RigidBody;
import Model.food.Food;
import processing.core.PVector;

import java.util.*;

public class SnakeBody implements Carnal {
    private Snake snake;
    private RigidBody head;
    private SnakeTail tail;
    private PVector momentum;
    private PVector inclination;
    private boolean turn;

    public SnakeBody(Snake snake, int headX, int headY, int segments) {
        this.snake = snake;
        head = RigidBody.newBall(snake,7,headX,headY);
        tail = new SnakeTail(snake,headX, headY, segments);

        momentum = new PVector(4,0);
        inclination = new PVector(0.01f,0);
        turn = false;
    }

    public SnakeTail getTail() {
        return tail;
    }

    public RigidBody getHead(){
        return head;
    }

    public PVector getMomentum() {
        return momentum;
    }

    public PVector getInclination() {
        return inclination;
    }

    public void move(){
        PVector headStep = new PVector(momentum.x,momentum.y),
                lastHeadPosition = new PVector(head.getPosition().x, head.getPosition().y);
        headStep.rotate(inclination.x);
        if(turn)turn = false;
        else inclination.rotate(0.2f);
        head.translate(headStep);
        tail.move(lastHeadPosition);
        if(head.collide()){
            for(Body it : head.getCollided()){
                if(it.getOwner() instanceof Food){
                    Food food = (Food)it.getOwner();
                    food.loadEffect(snake.getEffects());
                    food.consumed();
                }
                else momentum.set(0,0);
            }
            head.clearCollided();
        }
    }

    public void turnLeft(){
        turn(0.1f);
    }

    public void turnRight(){
        turn(-0.1f);
    }

    public void turn(float angle){
        momentum.rotate(angle);
        turn = true;
    }

    @Override
    public void loadBody(List<RelativisticBody> list) {
        list.add(new RelativisticBody.Builder(head).addDwell(Snake.headDimension).get());
        tail.loadBody(list);
    }
}
