import Alvic.Apk;
import Alvic.control.KeyMap;
import Alvic.control.KeyboardController;
import Alvic.control.MouseMap;
import Alvic.manage.Manager;
import Alvic.utilities.shop.contract.Contract;
import Model.ImageDealer;
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
        Apk.init(this,new Manager(1,1,1),new KeyMap(),new MouseMap());
        Apk.manager.getPrinter().setBackgroundColor(0xFFE49739);
    }

    @Override
    public void setup() {

        new ImageDealer().employ(Apk.shop);

        Apk.applet.rectMode(Apk.applet.CENTER);
        Apk.applet.imageMode(Apk.applet.CENTER);

        Apk.manager.add(new Box(Apk.applet.width - 20, Apk.applet.height - 20,
                Apk.applet.width / 2, Apk.applet.height / 2), -100);
        Apk.manager.add(new Wall(40,40,200,300));
//        Apk.manager.add(new Test());
        Snake snake = new Snake(new KeyboardController());
        Apk.shop.deliver(Contract.forClass(Snake.class), snake);
        Apk.manager.add(snake);
        Apk.manager.add(new FoodSupplier(10));
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
