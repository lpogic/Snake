package Alvic.utilities.graphs.models;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Lexicon<B,T,G> {
    void addBeing(B being);
    void removeBeing(B being);
    Set<B> getBeings();
    B getBeing();
    int countBeings();
    boolean containsBeing(B being);
    void removeTrait(B being, T trait);
    Set<T> getTraits(B being);
    T getTrait(B being);
    int countTraits(B being);
    boolean hasTrait(B being, T trait);
    void gradate(B being, T trait, G grade);
    List<G> getGrades();
    Collection<G> getGrades(B being);
    G getGrade(B being, T trait);
}
