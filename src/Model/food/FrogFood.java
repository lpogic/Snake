package Model.food;

import Alvic.Apk;
import Alvic.print.ImageOutfit;
import Alvic.react.body.RelativisticBody;
import Alvic.react.body.RigidBody;
import Alvic.react.figure.RoundFigure;
import Alvic.update.Alive;
import Model.ImageDealer;
import Model.Lottery;
import Model.effect.Effect;
import Model.effect.GrowEffect;
import Model.snake.Snake;
import Model.wall.Wall;
import processing.core.PVector;

import java.util.Collection;
import java.util.List;

public class FrogFood extends Food implements Alive {

    public static final Object frogRadarDimension = new Object();

    private enum FrogState{
        SITTING, PUMPING, JUMPING, CONSUMED;

        boolean consumable(){return this == SITTING || this == PUMPING;}
    }

    private RigidBody radar;
    private ImageOutfit sittingOutfit;
    private ImageOutfit pumpingOutfit;
    private ImageOutfit jumpingLeftOutfit;
    private ImageOutfit jumpingRightOutfit;
    private Lottery<FrogState> stateLottery;
    private FrogState state;
    private PVector jumpTarget;
    private long pumpingTime;

    public FrogFood(FoodSupplier owner, float x, float y) {
        super(owner);
        setBody(RigidBody.newBall(this, 9, x, y));
        radar = new RigidBody(this, new RoundFigure(new PVector(0,0), 25f),
                new RoundFigure(getBody().getPosition(),25f));
        sittingOutfit = new ImageOutfit(getBody(), Apk.shop.deal(ImageDealer.sittingFrog));
        pumpingOutfit = new ImageOutfit(getBody(), Apk.shop.deal(ImageDealer.pumpingFrog));
        jumpingLeftOutfit = new ImageOutfit(getBody(), Apk.shop.deal(ImageDealer.jumpingFrogLeft));
        jumpingRightOutfit = new ImageOutfit(getBody(), Apk.shop.deal(ImageDealer.jumpingFrogRight));
        setOutfit(sittingOutfit);
        stateLottery = new Lottery<>();
        stateLottery.setDrawChance(FrogState.SITTING, 1000);
        stateLottery.setDrawChance(FrogState.PUMPING, 10);
        stateLottery.setDrawChance(FrogState.JUMPING, 2);
        state = FrogState.SITTING;
    }

    @Override
    public void consumed() {
        if(state.consumable()) {
            super.consumed();
            state = FrogState.CONSUMED;
        }
    }

    @Override
    public void loadEffect(Collection<Effect> collector) {
        if(state == FrogState.CONSUMED) {
            collector.add(new GrowEffect(2));
            state = FrogState.SITTING;
        }
        super.loadEffect(collector);
    }

    @Override
    public void update() {
        PVector temp;
        if(radar.collide()){
            stateLottery.setDrawChance(FrogState.JUMPING, 200);
            radar.clearCollided();
        } else stateLottery.setDrawChance(FrogState.JUMPING, 2);
        if(state == FrogState.SITTING){
            stateLottery.draw();
            switch (stateLottery.getLoot()){
                case JUMPING:
                    temp = PVector.random2D();
                    temp.setMag(30);
                    jumpTarget = PVector.add(temp,getBody().getPosition());
                    setOutfit(temp.x > 0 ? jumpingRightOutfit : jumpingLeftOutfit);
                    state = FrogState.JUMPING;
                    break;
                case PUMPING:
                    setOutfit(pumpingOutfit);
                    pumpingTime = System.currentTimeMillis() + 1000;
                    state = FrogState.PUMPING;
                    break;
            }
        }
        if(state == FrogState.JUMPING){
            temp = PVector.sub(jumpTarget, getBody().getPosition());
            if(temp.mag() < 2.0f){
                getBody().setPosition(jumpTarget);
                setOutfit(sittingOutfit);
                state = FrogState.SITTING;
                if(Apk.manager.getReactor().collide(RelativisticBody.builder(getBody()).addAffect(Wall.wallDimension).get())){
                    consumed();
                }
            } else {
                temp.setMag(2.0f);
                getBody().translate(temp);
            }
        }
        if(state == FrogState.PUMPING){
            if(System.currentTimeMillis() > pumpingTime){
                setOutfit(sittingOutfit);
                state = FrogState.SITTING;
            }
        }
    }

    @Override
    public void loadBody(List<RelativisticBody> collector) {
        collector.add(RelativisticBody.builder(radar).addDwell(frogRadarDimension).get());
        super.loadBody(collector);
    }
}
