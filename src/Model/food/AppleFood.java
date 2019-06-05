package Model.food;

import Alvic.Apk;
import Alvic.print.ImageOutfit;
import Alvic.react.body.RigidBody;
import Alvic.update.Alive;
import Model.ImageDealer;
import Model.effect.ColorEffect;
import Model.effect.DrunkEffect;
import Model.effect.Effect;
import Model.effect.GrowEffect;

import java.util.Collection;

public class AppleFood extends Food implements Alive {

    private enum AppleState{
        FRESH, STALE, ROTTEN
    }
    private AppleState state;
    private long expirationMoment;
    private long decayMoment;
    private long returnMoment;

    public AppleFood(FoodSupplier owner, float x, float y) {
        super(owner);
        setBody(RigidBody.newBall(this, 9, x, y));
        setOutfit(new ImageOutfit(getBody(), Apk.shop.deal(ImageDealer.freshApple)));
        state = AppleState.FRESH;
        expirationMoment = System.currentTimeMillis() + 5000;
        decayMoment = System.currentTimeMillis() + 10000;
        returnMoment = System.currentTimeMillis() + 15000;
    }

    @Override
    public void loadEffect(Collection<Effect> collector) {
        switch (state) {
            case STALE:
                collector.add(new DrunkEffect( 2000, 0.1));
                collector.add(new ColorEffect(2000, 0xFF592250, 0xFF64084E));
            case FRESH:
                collector.add(new GrowEffect(2));
                break;
            case ROTTEN:
                collector.add(new DrunkEffect( 2000, 0.5));
                collector.add(new ColorEffect(2000, 0xFF64084E, 0xFF3E1253));
        }
        super.loadEffect(collector);

    }

    @Override
    public void update() {
        long currentTime = System.currentTimeMillis();
        if(currentTime > expirationMoment){
            if(currentTime > decayMoment){
                if(currentTime > returnMoment)consumed();
                else if(state != AppleState.ROTTEN){
                    setOutfit(new ImageOutfit(getBody(), Apk.shop.deal(ImageDealer.rottenApple)));
                    state = AppleState.ROTTEN;
                }
            } else {
                if(state != AppleState.STALE){
                    setOutfit(new ImageOutfit(getBody(), Apk.shop.deal(ImageDealer.staleApple)));
                    state = AppleState.STALE;
                }
            }
        }
    }
}
