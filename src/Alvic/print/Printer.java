package Alvic.print;

import Alvic.Apk;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Printer implements Visible{

    private List<OrderedVisible> paintings;
    private PImage backgroundImage;
    private int backgroundColor;

    private Printer(PImage backgroundImage, int backgroundColor){
        paintings = new ArrayList<>();
        if(backgroundImage != null)backgroundImage.resize(Apk.applet.width,Apk.applet.height);
        backgroundImage = backgroundImage;
        this.backgroundColor = backgroundColor;
    }

    public Printer(int backgroundColor){
        this(null,backgroundColor);
    }

    public Printer(PImage backgroundImage){
        this(backgroundImage,0xFF000000);
    }

    public PImage getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(PImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void add(Visible visible, int layer){
        int newPos = -1, oldPos = -1;
        for(int i = 0;i < paintings.size();++i){
            if(newPos < 0 && layer <= paintings.get(i).layer)newPos = i;
            if(oldPos < 0 && visible.equals(paintings.get(i).visible))oldPos = i;
        }
        if(oldPos >= 0 && oldPos != newPos){
            if(oldPos < newPos)--newPos;
            paintings.remove(oldPos);
        }
        if(newPos < 0)paintings.add(new OrderedVisible(visible, layer));
        else paintings.add(newPos, new OrderedVisible(visible, layer));
    }

    public void add(Visible visible){
        add(visible,0);
    }

    public void remove(Visible visible){
        int pos = -1;
        for(int i = 0;i < paintings.size() && pos < 0;++i){
            if(visible.equals(paintings.get(i).visible))pos = i;
        }
        if(pos >= 0)paintings.remove(pos);
    }
    
    public void clear(){
        paintings = new ArrayList<>();
    }

    public int size(){
        return paintings.size();
    }

    public void changeLayer(Visible visible, int newLayer) {
        add(visible,newLayer);
    }

    @Override
    public void print() {
        if ((backgroundColor & 0xFF000000) != 0)
            Apk.applet.background(backgroundColor);
        if (backgroundImage != null)
            Apk.applet.background(backgroundImage);

        paintings.forEach(p->p.visible.print());
    }

    private class OrderedVisible{
        private Visible visible;
        private int layer;

        public OrderedVisible(Visible visible, int layer) {
            this.visible = visible;
            this.layer = layer;
        }
    }
}
