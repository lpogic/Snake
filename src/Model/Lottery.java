package Model;


import java.util.HashMap;
import java.util.Map;

public class Lottery<T> {
    private Map<T, Integer> awards;
    private T loot;
    private int totalChance;

    public Lottery() {
        reset();
    }

    public void setDrawChance(T item, int chance){
        if(chance > 0) {
            totalChance += chance - awards.getOrDefault(item, 0);
            awards.put(item, chance);
        } else awards.remove(item);
    }

    public final void reset(){
        awards = new HashMap<>();
        loot = null;
    }

    public boolean draw(){
        if(totalChance <= 0)return false;
        int event = (int)(Math.random() * totalChance);
        for(T it : awards.keySet()){
            event-= awards.get(it);
            if(event <= 0){
                return (loot = it) != null;
            }
        }
        return false;
    }

    public T getLoot(){
        return loot;
    }
}
