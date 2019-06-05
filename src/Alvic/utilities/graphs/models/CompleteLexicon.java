package Alvic.utilities.graphs.models;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface CompleteLexicon<B,T,G> extends Lexicon<B,T,G> {
    void addTrait(T trait);
    void removeTrait(T trait);
    Set<T> getTraits();
    T getTrait();
    int countTraits();
    boolean containsTrait(T trait);
    Set<B> getBeings(T trait);
    B getBeing(T trait);
    int countBeings(T trait);
    boolean hasBeing(T trait, B being);
    void gradate(B being, T trait, G traitInBeing, G beingInTrait);
    List<G> getBeingGrades();
    Collection<G> getBeingGrades(T trait);
    G getBeingGrade(T trait, B being);
}
