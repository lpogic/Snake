package Alvic.react.body;

import Alvic.react.Carnal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BodyComposite implements Body{

    private List<Body> compounds;
    private Set<Body> collided;
    private Carnal owner;

    public BodyComposite(Carnal owner) {
        this.owner = owner;
        compounds = new ArrayList<>();
        collided = new HashSet<>();
    }

    public void add(Body body){
        compounds.add(body);
    }

    public void remove(Body body){
        compounds.remove(body);
    }

    @Override
    public boolean collide(Body body) {
        for(Body it : compounds){
            if(it.collide(body)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void collision(Body body) {
        collided.add(body);
    }

    public boolean collide(){
        return !collided.isEmpty();
    }

    public Set<Body> getCollided(){
        return collided;
    }

    public void clearCollided(){
        collided = new HashSet<>();
    }

    @Override
    public Carnal getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "CompositeBody of " + owner.toString();
    }
}
