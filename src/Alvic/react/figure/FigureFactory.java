package Alvic.react.figure;

import processing.core.PVector;

public class FigureFactory {

    public static CarvedFigure makeRectangle(float w, float h){
        return new CarvedFigure(new PVector(w / 2, h / 2),
                new PVector(-w, 0),
                new PVector(0, -h),
                new PVector(w, 0),
                new PVector(0, h));
    }

    public static CompoundFigure makeAntiRectangle(float w, float h){
        CompoundFigure composite = new CompoundFigure();
        composite.add(new CarvedFigure(new PVector(w / 2, h / 2), new PVector(0, -h)));
        composite.add(new CarvedFigure(new PVector(w / 2, -h / 2), new PVector(-w, 0)));
        composite.add(new CarvedFigure(new PVector(-w / 2, -h / 2), new PVector(0, h)));
        composite.add(new CarvedFigure(new PVector(-w / 2, h / 2), new PVector(w, 0)));
        return composite;
    }
}
