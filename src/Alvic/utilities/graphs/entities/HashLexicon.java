/**
 * Created by LPO on 08.09.2018
 */

package Alvic.utilities.graphs.entities;

import Alvic.utilities.graphs.models.Lexicon;

import java.util.*;

public class HashLexicon<B,T,G> implements Lexicon<B,T,G> {
    protected Map<B, Map<T,G>> data;
    protected Map<T,G> fakeTraits;

    public HashLexicon(){
        data = new HashMap<>();
        fakeTraits = new HashMap<>(0);
    }

    protected final void set(HashLexicon<B,T,G> that){
        this.data = that.data;
    }

    @Override
    public void addBeing(B being){
        data.putIfAbsent(being,new HashMap<>());
    }

    @Override
    public void removeBeing(B being){
        data.remove(being);
    }

    @Override
    public Set<B> getBeings(){
        return data.keySet();
    }

    @Override
    public B getBeing(){
        Set<B> nodes = data.keySet();
        return nodes.isEmpty() ? null : nodes.iterator().next();
    }

    @Override
    public int countBeings(){
        return data.keySet().size();
    }

    @Override
    public boolean containsBeing(B being){
        return data.containsKey(being);
    }

    @Override
    public void removeTrait(B being, T trait){
        data.getOrDefault(being,fakeTraits).remove(trait);
    }

    @Override
    public Set<T> getTraits(B being){
        return data.getOrDefault(being,fakeTraits).keySet();
    }

    @Override
    public T getTrait(B being) {
        Set<T> traits = getTraits(being);
        return traits.isEmpty() ? null : traits.iterator().next();
    }

    @Override
    public int countTraits(B being){
        return getTraits(being).size();
    }

    @Override
    public boolean hasTrait(B being, T trait){
        return getGrade(being,trait) != null;
    }

    @Override
    public void gradate(B being, T trait, G grade){
        addBeing(being);
        data.get(being).put(trait,grade);
    }

    @Override
    public List<G> getGrades(){
        List<G> grades = new ArrayList<>();
        for(Map<T,G> it : data.values()){
            grades.addAll(it.values());
        }
        return grades;
    }

    @Override
    public Collection<G> getGrades(B being){
        return data.getOrDefault(being,fakeTraits).values();
    }

    @Override
    public G getGrade(B being, T trait){
        return data.getOrDefault(being,fakeTraits).get(trait);
    }
}
