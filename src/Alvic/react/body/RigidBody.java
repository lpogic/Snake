package Alvic.react.body;

import Alvic.react.Carnal;
import Alvic.react.figure.Figure;
import Alvic.react.figure.RoundFigure;
import Alvic.react.figure.SpaceFigure;
import Alvic.utilities.PVectorUtil;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RigidBody implements Body {

    public static RigidBody newBall(Carnal owner, float r, float x, float y){
        return new RigidBody(owner, x,y,new RoundFigure(new PVector(0,0),r),r);
    }

    public class MVector{
        private PVector absolute;
        private PVector relative;

        public MVector(PVector vector){
            this.absolute = new PVector(vector.x,vector.y);
            this.relative = vector;
        }

        public MVector(PVector absolute, PVector relative) {
            this.absolute = absolute;
            this.relative = relative;
        }

        public void rotate(PVector rotation){
            relative.set(PVectorUtil.det(absolute,rotation), PVectorUtil.dot(absolute,rotation));
        }

        public void translate(PVector transposition){
            relative.set(absolute.x + transposition.x,absolute.y + transposition.y);
        }

        public void relate(PVector rotation, PVector translation){
            relative.set(PVectorUtil.det(absolute,rotation) + translation.x,
                    PVectorUtil.dot(absolute,rotation) + translation.y);
        }

        public PVector getRelative() {
            return relative;
        }
    }

    private Carnal owner;
    private float rotation;
    private PVector position;
    private Figure figure;
    private List<MVector> vertices;
    private List<MVector> offsets;
    private RoundFigure wrapper;
    private boolean figureSynchronized;
    private Set<Body> collided;

    public RigidBody(Carnal owner, float xPosition, float yPosition, Figure figure, float wrapperRadius){
        this(owner, figure, new RoundFigure(new PVector(xPosition, yPosition), wrapperRadius));
    }

    public RigidBody(Carnal owner, Figure figure, RoundFigure wrapper){
        this.owner = owner;
        this.figure = figure;
        PVector[] vectors = figure.getInterVector();
        vertices = new ArrayList<>(vectors.length);
        for(PVector it : vectors){
            vertices.add(new MVector(it));
        }
        vectors = figure.getOuterVector();
        offsets = new ArrayList<>(vectors.length);
        for(PVector it : vectors){
            offsets.add(new MVector(it));
        }
        rotation = 0;
        position = wrapper.getOffset();
        figureSynchronized = false;
        this.wrapper = wrapper;
        collided = new HashSet<>();
    }

    public Figure getFigure() {
        return figure;
    }

    public RoundFigure getWrapper() {
        return wrapper;
    }

    public List<MVector> getVertices() {
        return vertices;
    }

    public List<MVector> getOffsets() {
        return offsets;
    }

    public void setOwner(Carnal owner) {
        this.owner = owner;
    }

    public void rotate(float angle){
        rotation+= angle;
        figureSynchronized = false;
    }

    public void translate(PVector shift){
        position.add(shift);
        figureSynchronized = false;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
        figureSynchronized = false;
    }

    public void setPosition(PVector position) {
        this.position.set(position);
        figureSynchronized = false;
    }

    public float getRotation() {
        return rotation;
    }

    public PVector getPosition() {
        return position;
    }

    @Override
    public boolean collide(Body that){
        if(that instanceof RigidBody)return collide((RigidBody)that);
        return that.collide(this);
    }

    private boolean collide(RigidBody that){
        if(wrapper.intersect(that.wrapper)){
            if(!figureSynchronized)synchronizeFigure();
            if(!that.figureSynchronized)that.synchronizeFigure();
            return figure.intersect(that.figure);
        }
        return false;
    }

    @Override
    public void collision(Body body) {
        collided.add(body);
    }

    public boolean collide(){
        return !collided.isEmpty();
    }

    public Set<Body> getCollided(){
        return collided;
    }

    public void clearCollided(){
        collided = new HashSet<>();
    }

    public boolean isFigureSynchronized(){
        return figureSynchronized;
    }

    public void synchronizeFigure(){
        PVector rotationVector = new PVector(PApplet.sin(rotation),PApplet.cos(rotation));
        for(MVector it : vertices){
            it.rotate(rotationVector);
        }
        for(MVector it : offsets){
            it.relate(rotationVector,position);
        }
        figureSynchronized = true;
    }

    @Override
    public Carnal getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Body of " + owner;
    }
}
