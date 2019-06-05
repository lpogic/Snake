package Alvic.print;

import Alvic.Apk;
import Alvic.react.body.RigidBody;
import processing.core.PImage;

public class ImageOutfit extends RigidBodyOutfit {

    private PImage image;

    public ImageOutfit(RigidBody body, PImage image) {
        super(body);
        this.image = image;
    }

    public PImage getImage() {
        return image;
    }

    @Override
    public void print() {
        Apk.applet.image(image, getBody().getPosition().x, getBody().getPosition().y);
    }
}
