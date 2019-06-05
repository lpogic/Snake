package Alvic;

import Alvic.control.KeyMap;
import Alvic.control.MouseMap;
import Alvic.manage.Manager;
import Alvic.utilities.shop.Shop;
import processing.core.PApplet;

public class Apk {
    public static PApplet applet;
    public static Manager manager;
    public static KeyMap keyboard;
    public static MouseMap mouse;
    public static Shop shop;

    private Apk(){}

    public static void init(PApplet applet, Manager manager, KeyMap keymap, MouseMap mousemap){
        Apk.applet = applet;
        Apk.manager = manager;
        Apk.keyboard = keymap;
        Apk.mouse = mousemap;
        Apk.shop = new Shop();
    }

    public static void debug(Object ... objects){
        debug(20,objects);
    }

    public static void debug(int offsetX, Object ... objects){
        Apk.applet.fill(0);
        int y = 20;
        for(Object it : objects){
            Apk.applet.text(it.toString(),offsetX,y);
            y+= 20;
        }

    }
}
