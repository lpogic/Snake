package Alvic.utilities.graphs.entities;


import Alvic.utilities.graphs.models.CompleteLexicon;
import Alvic.utilities.graphs.models.Lexicon;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CompleteHashLexicon<B,T,G> extends HashLexicon<B,T,G> implements CompleteLexicon<B,T,G> {
    private Lexicon<T,B,G> antiLexicon;

    public CompleteHashLexicon(){
        super();
        antiLexicon = new HashLexicon<>();
    }

    @Override
    public void removeTrait(B being, T trait){
        super.removeTrait(being,trait);
        antiLexicon.removeTrait(trait,being);
    }

    @Override
    public void removeBeing(B being){
        for(T it : getTraits(being)){
            antiLexicon.removeTrait(it,being);
        }
        super.removeBeing(being);
    }

    @Override
    public void gradate(B being, T trait, G grade) {
        gradate(being,trait,grade,grade);
    }

    @Override
    public void addTrait(T trait) {
        antiLexicon.addBeing(trait);
    }

    @Override
    public void removeTrait(T trait) {
        for(B it : antiLexicon.getTraits(trait)){
            super.removeTrait(it,trait);
        }
        antiLexicon.removeBeing(trait);
    }

    @Override
    public Set<T> getTraits() {
        return antiLexicon.getBeings();
    }

    @Override
    public T getTrait() {
        return antiLexicon.getBeing();
    }

    @Override
    public int countTraits() {
        return antiLexicon.countBeings();
    }

    @Override
    public boolean containsTrait(T trait) {
        return antiLexicon.containsBeing(trait);
    }

    @Override
    public Set<B> getBeings(T trait) {
        return antiLexicon.getTraits(trait);
    }

    @Override
    public B getBeing(T trait) {
        return antiLexicon.getTrait(trait);
    }

    @Override
    public int countBeings(T trait) {
        return antiLexicon.countTraits(trait);
    }

    @Override
    public boolean hasBeing(T trait, B being) {
        return antiLexicon.hasTrait(trait,being);
    }

    @Override
    public void gradate(B being, T trait, G traitInBeing, G beingInTrait) {
        super.gradate(being,trait,traitInBeing);
        antiLexicon.gradate(trait,being,beingInTrait);
    }

    @Override
    public List<G> getBeingGrades() {
        return antiLexicon.getGrades();
    }

    @Override
    public Collection<G> getBeingGrades(T trait) {
        return antiLexicon.getGrades(trait);
    }

    @Override
    public G getBeingGrade(T trait, B being) {
        return antiLexicon.getGrade(trait,being);
    }
}
