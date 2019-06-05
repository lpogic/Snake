/**
 * Created by LPO on 08.09.2018
 */

package Alvic.utilities.graphs.entities;

import Alvic.utilities.graphs.models.CompleteLexicon;

import java.util.Set;

public class HashClassifier<O,C> {

    private CompleteLexicon<O,C,Boolean> space;

    public HashClassifier(){
        space = new CompleteHashLexicon<>();
    }

    public Set<C> getClasses(O object){
        return space.getTraits(object);
    }

    public Set<O> getObjects(C _class){
        return space.getBeings(_class);
    }

    public O getObject(C _class){
        return space.getBeing(_class);
    }

    public void setClass(O object, C _class){
        space.removeBeing(object);
        space.gradate(object,_class,true);
    }

    public void addClass(O object, C _class){
        space.gradate(object,_class,true);
    }

    @SafeVarargs
    public final void addClasses(O object, C ... classes){
        for(C it : classes){
            addClass(object,it);
        }
    }

    public void removeClass(O object, C _class){
        space.removeTrait(object,_class);
    }

    public boolean hasClass(O object, C _class){
        return space.hasTrait(object,_class);
    }

    public void removeObject(O object) {
        space.removeBeing(object);
    }
}
