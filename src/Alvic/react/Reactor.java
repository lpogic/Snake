package Alvic.react;

import Alvic.react.body.Body;
import Alvic.react.body.RelativisticBody;
import Alvic.utilities.graphs.entities.DirectedExtendedHashGraph;
import Alvic.utilities.graphs.entities.HashClassifier;
import Alvic.utilities.graphs.models.DirectedGraph;

import java.util.*;

/**
 * Relations graph format:
 *  pike ---affect---> quill
 */
public class Reactor {

    private HashClassifier<Body, Object> pikes;
    private HashClassifier<Body, Object> quills;
    private DirectedGraph<Body, Object> relations;
    private List<RelativisticBody> collector;
    private int size;

    public Reactor() {
        pikes = new HashClassifier<>();
        quills = new HashClassifier<>();
        relations = new DirectedExtendedHashGraph<>();
        collector = new LinkedList<>();
        size = 0;
    }

    public void add(Carnal carnal){
        carnal.loadBody(collector);
        for(RelativisticBody it : collector){
            for(Object dimension : it.getDwellDimensions()){
                for(Body pike : pikes.getObjects(dimension)){
                    relations.link(pike,it.getBody(),true);
                }
            }
            for(Object dimension : it.getAffectDimensions()){
                for(Body quill : quills.getObjects(dimension)){
                    relations.link(it.getBody(), quill,true);
                }
            }
            it.getDwellDimensions().forEach(d->quills.addClass(it.getBody(),d));
            it.getAffectDimensions().forEach(d->pikes.addClass(it.getBody(),d));
        }
        collector.clear();
    }

    public void remove(Carnal carnal){
        carnal.loadBody(collector);
        for(RelativisticBody it : collector){
            relations.removeNode(it.getBody());
            pikes.removeObject(it.getBody());
            quills.removeObject(it.getBody());
        }
        collector.clear();
    }

    public void clear(){
        relations = new DirectedExtendedHashGraph<>();
        pikes = new HashClassifier<>();
        quills = new HashClassifier<>();
        size = 0;
    }

    public int size(){
        return size;
    }

    public void react(){
        for(Body it : relations.getNodes()){
            for(Body itt : relations.getNodes(it)){
                if(itt.collide(it)){
                    itt.collision(it);
                }
            }
        }
    }

    public boolean collide(RelativisticBody rbody){
        for (Object dimension : rbody.getDwellDimensions()) {
            for (Body pike : pikes.getObjects(dimension)) {
                if(rbody.getBody().collide(pike))return true;
            }
        }
        for (Object dimension : rbody.getAffectDimensions()) {
            for (Body quill : quills.getObjects(dimension)) {
                if(quill.collide(rbody.getBody()))return true;
            }
        }
        return false;
    }
}
