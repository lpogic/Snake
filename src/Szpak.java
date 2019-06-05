import Alvic.Apk;
import Alvic.control.KeyMap;
import Alvic.control.KeyboardController;
import Alvic.control.MouseMap;
import Alvic.manage.Manager;
import Model.ImageDealer;
import Model.menu.Menu;
import Model.wall.Box;
import Model.food.FoodSupplier;
import Model.snake.Snake;
import Model.wall.Wall;
import processing.core.PApplet;

public class Szpak extends PApplet {

    public static void main(String[] args) {
        PApplet.main("Szpak");
    }

    @Override
    public void settings() {
        size(700,600);
        Apk.init(this,null,new KeyMap(),new MouseMap());
    }

    @Override
    public void setup() {

        new ImageDealer().employ(Apk.shop);

        Apk.applet.rectMode(Apk.applet.CENTER);
        Apk.applet.imageMode(Apk.applet.CENTER);

        new Menu(new KeyboardController()).newGame();
    }

    @Override
    public void draw() {
        Apk.manager.step();
    }

    @Override
    public void keyPressed() {
        Apk.keyboard.setKey(keyCode);
    }

    @Override
    public void keyReleased() {
        Apk.keyboard.resetKey(keyCode);
    }

    @Override
    public void mousePressed() {
        Apk.mouse.setButton(mouseX,mouseY,mouseButton);
    }

    @Override
    public void mouseReleased() {
        Apk.mouse.resetButton(mouseX,mouseY,mouseButton);
    }
}
