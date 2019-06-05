package Alvic.update;

import java.util.HashSet;
import java.util.Set;

public class Updater implements Alive{

    private Set<Alive> aliveList;

    public Updater(){
        aliveList = new HashSet<>();
    }

    public boolean add(Alive alive){
        return aliveList.add(alive);
    }

    public boolean remove(Alive alive){
        return aliveList.remove(alive);
    }

    public void clear(){
        aliveList = new HashSet<>();
    }

    public int size(){
        return aliveList.size();
    }

    @Override
    public void update(){
        aliveList.forEach(Alive::update);
    }
}
