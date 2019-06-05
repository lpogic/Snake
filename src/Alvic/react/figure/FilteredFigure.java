package Alvic.react.figure;

import java.util.Collection;

public class FilteredFigure extends FigureList{

    public FilteredFigure() {
        super();
    }

    public FilteredFigure(Figure... figures){
        super(figures);
    }

    public FilteredFigure(Collection<Figure> figures){
        super(figures);
    }

    @Override
    public boolean intersect(Figure that) {
        for(Figure it : getFigures())
            if(!it.intersect(that))return false;
        return true;
    }
}
