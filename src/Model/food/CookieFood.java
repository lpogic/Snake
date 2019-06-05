package Model.food;

import Alvic.print.ZorbaOutfit;
import Alvic.react.body.RigidBody;
import Model.effect.ColorEffect;
import Model.effect.DrunkEffect;
import Model.effect.Effect;
import Model.effect.GrowEffect;

import java.util.Collection;

public class CookieFood extends Food {

    public CookieFood(FoodSupplier owner, int x, int y, int size) {
        super(owner);
        setBody(RigidBody.newBall(this, size, x, y));
        setOutfit(new ZorbaOutfit(getBody(),0xFF4C2411));
    }

    @Override
    public void loadEffect(Collection<Effect> collector) {
        collector.add(new GrowEffect(1));
        collector.add(new DrunkEffect( 2000, 0.1));
        collector.add(new ColorEffect(2000, 170, 200));
        super.loadEffect(collector);
    }

    @Override
    public void print() {
        super.print();

    }
}
