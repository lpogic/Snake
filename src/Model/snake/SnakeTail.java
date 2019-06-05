package Model.snake;

import Alvic.react.body.BodyComposite;
import Alvic.react.Carnal;
import Alvic.react.body.RelativisticBody;
import Alvic.react.body.RigidBody;
import processing.core.PVector;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class SnakeTail implements Carnal {

    private Snake snake;
    private Deque<RigidBody> segments;
    private BodyComposite headCollidingSegments;

    public SnakeTail(Snake snake, int headX, int headY, int segments) {
        this.snake = snake;
        this.segments = new ArrayDeque<>();
        headCollidingSegments = new BodyComposite(snake);
        for(int i = 0;i < segments;++i){
            RigidBody segment = RigidBody.newBall(snake, 1,headX,headY);
            this.segments.add(segment);
        }
    }

    public Deque<RigidBody> getSegments() {
        return segments;
    }

    public void move(PVector newPosition){
        PVector temp;
        for(RigidBody it : segments){
            temp = PVector.sub(newPosition, it.getPosition());
            if(temp.mag() < 5f)return;
            temp = new PVector(it.getPosition().x, it.getPosition().y);
            it.setPosition(newPosition);
            newPosition = temp;
        }
        if(headCollidingSegments.collide()) System.out.println("collide");
    }

    public void enlarge(){
        for(int i = 0;i < 1;++i) {
            PVector tailPosition = segments.getLast().getPosition();
            RigidBody segment = RigidBody.newBall(snake, 1, tailPosition.x, tailPosition.y);
            segments.add(segment);
            headCollidingSegments.add(segment);
        }
    }

    @Override
    public void loadBody(List<RelativisticBody> collector) {
        collector.add(new RelativisticBody.Builder(headCollidingSegments).addAffect(Snake.headDimension).get());
    }
}
