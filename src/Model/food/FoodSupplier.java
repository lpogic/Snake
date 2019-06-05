package Model.food;

import Alvic.Apk;
import Alvic.react.body.RelativisticBody;
import Alvic.update.Alive;
import Alvic.utilities.PVectorUtil;
import Model.Lottery;
import Model.snake.Snake;
import Model.wall.Wall;
import processing.core.PVector;

import java.util.HashSet;
import java.util.Set;

public class FoodSupplier implements Alive {

    public enum FoodType{
        APPLE, FROG
    }

    private Lottery<FoodType> typeLottery;
    private Lottery<Boolean> successLottery;
    private Set<Food> supplied;
    private int maxSuppliedFoods;

    public FoodSupplier(int maxSuppliedFoods) {
        this.maxSuppliedFoods = maxSuppliedFoods;
        typeLottery = new Lottery<>();
        typeLottery.setDrawChance(FoodType.FROG, 10);
        typeLottery.setDrawChance(FoodType.APPLE, 30);
        successLottery = new Lottery<>();
        successLottery.setDrawChance(null, 100);
        successLottery.setDrawChance(true, 5);
        supplied = new HashSet<>();
    }

    public void reportConsumption(Food food){
        supplied.remove(food);
        Apk.manager.remove(food);
    }

    @Override
    public void update() {
        if(supplied.size() < maxSuppliedFoods && successLottery.draw()) {
            supplyFood();
        }
    }

    public void supplyFood(){
        if (!typeLottery.draw())return;
        Food food;
        PVector position = PVectorUtil.randomPosition(Apk.applet.width, Apk.applet.height);
        switch (typeLottery.getLoot()) {
            case FROG:
                food = new FrogFood(this, position.x, position.y);
                break;
            default:
                food = new AppleFood(this, position.x, position.y);
                break;
        }
        RelativisticBody relFood = RelativisticBody.builder(food.getBody())
                .addAffect(Wall.wallDimension, Snake.headDimension).get();
        int watchdog = 0;
        while(Apk.manager.getReactor().collide(relFood)){
            if(watchdog++ > 50){
                System.out.println("Food supply failed - cannot find clear position");
                return;
            }
            food.setPosition(PVectorUtil.randomPosition(Apk.applet.width, Apk.applet.height));
        }
        Apk.manager.add(food);
        supplied.add(food);
    }
}
