package Model;

import Alvic.Apk;
import Alvic.utilities.shop.Dealer;
import Alvic.utilities.shop.Shop;
import Alvic.utilities.shop.contract.Contract;
import Alvic.utilities.shop.contract.stamp.Stamp;
import processing.core.PImage;

public class ImageDealer implements Dealer {

    public static final Contract<PImage> freshApple = Contract.forObjectOf(PImage.class, Stamp.WARRANTY);
    public static final Contract<PImage> staleApple = Contract.forObjectOf(PImage.class, Stamp.WARRANTY);
    public static final Contract<PImage> rottenApple = Contract.forObjectOf(PImage.class, Stamp.WARRANTY);
    public static final Contract<PImage> jumpingFrogLeft = Contract.forObjectOf(PImage.class, Stamp.WARRANTY);
    public static final Contract<PImage> jumpingFrogRight = Contract.forObjectOf(PImage.class, Stamp.WARRANTY);
    public static final Contract<PImage> sittingFrog = Contract.forObjectOf(PImage.class, Stamp.WARRANTY);
    public static final Contract<PImage> pumpingFrog = Contract.forObjectOf(PImage.class, Stamp.WARRANTY);

    @Override
    public void employ(Shop shop) {
        shop.deliver(freshApple,Apk.applet.loadImage("fresh_apple.png"));
        shop.deliver(staleApple,Apk.applet.loadImage("stale_apple.png"));
        shop.deliver(rottenApple,Apk.applet.loadImage("rotten_apple.png"));
        shop.deliver(sittingFrog,Apk.applet.loadImage("sitting_frog.png"));
        shop.deliver(pumpingFrog,Apk.applet.loadImage("pumping_frog.png"));
        shop.deliver(jumpingFrogLeft,Apk.applet.loadImage("jumping_frog_left.png"));
        shop.deliver(jumpingFrogRight,Apk.applet.loadImage("jumping_frog_right.png"));
    }
}
