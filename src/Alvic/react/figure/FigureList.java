package Alvic.react.figure;

import processing.core.PVector;

import java.util.*;

public class FigureList implements Figure {

    private List<Figure> figures;

    public FigureList() {
        figures = new ArrayList<>();
    }

    public FigureList(Figure... figures){
        this.figures = Arrays.asList(figures);
    }

    public FigureList(Collection<Figure> figures){
        this.figures = new ArrayList<>(figures);
    }

    public boolean add(Figure figure){
        return figures.add(figure);
    }

    public boolean remove(Figure figure){
        return figures.remove(figure);
    }

    public List<Figure> getFigures() {
        return figures;
    }

    @Override
    public boolean intersect(Figure that) {
        return false;
    }

    @Override
    public PVector[] getOuterVector() {
        Set<PVector> vectors = new HashSet<>();
        for(Figure it : figures){
            vectors.addAll(Arrays.asList(it.getOuterVector()));
        }
        return vectors.toArray(new PVector[0]);
    }

    @Override
    public PVector[] getInterVector() {
        Set<PVector> vectors = new HashSet<>();
        for(Figure it : figures){
            vectors.addAll(Arrays.asList(it.getInterVector()));
        }
        return vectors.toArray(new PVector[0]);
    }
}
