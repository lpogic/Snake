package Alvic.react.figure;

import java.util.Collection;

public class CompoundFigure extends FigureList{

    public CompoundFigure() {
        super();
    }

    public CompoundFigure(Figure... figures){
        super(figures);
    }

    public CompoundFigure(Collection<Figure> figures){
        super(figures);
    }

    @Override
    public boolean intersect(Figure that) {
        for(Figure it : getFigures())
            if(it.intersect(that))return true;
        return false;
    }
}
