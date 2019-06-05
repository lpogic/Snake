package Alvic.react.figure;

import processing.core.PVector;

public interface Figure {
    boolean intersect(Figure that);
    PVector[] getOuterVector();
    PVector[] getInterVector();
}
