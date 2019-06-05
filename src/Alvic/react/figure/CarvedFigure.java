package Alvic.react.figure;

import Alvic.Apk;
import Alvic.utilities.PVectorUtil;
import processing.core.PVector;

import java.util.Arrays;
import java.util.Collection;

public class CarvedFigure implements Figure {

    private PVector offset;
    private PVector[] vertices;

    public CarvedFigure(PVector offset, PVector... vertices){
        this.offset = offset;
        this.vertices = vertices;
    }

    public CarvedFigure(PVector offset, Collection<PVector> vertices){
        this.offset = offset;
        this.vertices = vertices.toArray(new PVector[0]);
    }

    public boolean intersect(Figure figure){
        if(figure == null)return false;
        if(figure instanceof RoundFigure)return intersect((RoundFigure) figure);
        if(figure instanceof CarvedFigure)return intersect((CarvedFigure) figure);
        return figure.intersect(this);
    }

    @Override
    public PVector[] getOuterVector() {
        return new PVector[]{offset};
    }

    @Override
    public PVector[] getInterVector() {
        return vertices;
    }

    /*
    Miejsce przeciecia dwoch wektorow:


                 det[(vb2 - vb1),v2]
    v = vb1 + v1 -------------------
                     det[v1,v2]


     */

    private boolean intersect(CarvedFigure that){
        PVector[][] relations= new PVector[vertices.length][that.vertices.length];
        PVector shift = PVector.sub(offset,that.offset);

        boolean pointInside;
        for(int i = 0;i < vertices.length;++i){ // punkty z this w that
            pointInside = true;
            for(int j = 0;j < that.vertices.length;++j){
                relations[i][j] = new PVector(shift.x,shift.y);
                if(pointInside)pointInside = PVectorUtil.det(that.vertices[j],shift) <= 0;
                shift.sub(that.vertices[j]);
            }
            if(pointInside)return true; // absolutna wspolrzedna that.base + relations[i][0]
            PVector.add(relations[i][0],vertices[i],shift);
        }

        for(int i = 0;i < that.vertices.length;++i){ // punkty z that w this
            pointInside = true;
            for(int j = 0;j < vertices.length && pointInside;++j){
                pointInside = PVectorUtil.det(relations[j][i],vertices[j]) <= 0;
            }
            if(pointInside)return true; // absolutna wspolrzedna this.base - relations[0][i]
        }
        return false;
    }

    private boolean intersect(RoundFigure circle) {
        PVector shift = PVector.sub(circle.getOffset(),offset), last;

        last = new PVector(shift.x,shift.y);
        for(PVector it : vertices){
            if(PVectorUtil.det(shift,it) > circle.getRadius()*it.mag()) // porownanie pol, odleglosc od scian
                return false;
            shift.sub(it);
        }

        shift = new PVector(last.x,last.y);
        last = vertices[vertices.length - 1];
        for(PVector it : vertices){
            if(PVectorUtil.dot(it,shift) < 0 && PVectorUtil.dot(last,shift) > 0 &&
                    shift.magSq() > circle.getRadius()*circle.getRadius()) { // katy
                return false;
            }
            last = it;
            shift.sub(it);
        }
        return true;
    }
}

