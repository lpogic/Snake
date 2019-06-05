package Model.menu;

import Alvic.Apk;
import Alvic.control.Controller;
import Alvic.control.KeyboardController;
import Alvic.control.MouseController;
import Alvic.manage.Manager;
import Alvic.print.Printer;
import Alvic.update.Alive;
import Alvic.update.Updater;
import Model.food.FoodSupplier;
import Model.snake.Snake;
import Model.wall.Box;
import Model.wall.Wall;

import java.awt.event.KeyEvent;

public class Menu implements Alive {

    private Printer printer;
    private Updater updater;
    private KeyboardController ctrl;
    private boolean spaceInduced;

    public Menu(KeyboardController ctrl) {
        ctrl.setKey(KeyEvent.VK_SPACE,1);
        this.ctrl = ctrl;

        printer = new Printer(0x10111111);
        updater = new Updater();
    }

    @Override
    public void update() {
        if(spaceInduced){
            if(!ctrl.induced(1))spaceInduced = false;
        } else {
            if(ctrl.induced(1)){

                spaceInduced = true;
            }
        }
    }

    public void newGame(){
        Apk.manager = new Manager(1,1,1);
        Apk.manager.getPrinter().setBackgroundColor(0xFFE49739);

        Apk.manager.add(new Box(Apk.applet.width - 20, Apk.applet.height - 20,
                Apk.applet.width / 2, Apk.applet.height / 2), -100);
        Apk.manager.add(new Wall(40,40,200,300));
//        Apk.manager.add(new Test());
        Snake snake = new Snake(new KeyboardController(), 100f, 500f, 4, 0);
        Apk.manager.add(snake);
        Apk.manager.add(new FoodSupplier(10));
    }

    public Snake makeKeyboardControlledSnake(float headX, float headY, float momentumX, float momentumY, int turnLeftKey, int turnRightKey){
        KeyboardController ctrl= new KeyboardController();
        ctrl.setKey(turnLeftKey,2);
        ctrl.setKey(turnRightKey,1);
        return new Snake(ctrl, headX, headY, momentumX, momentumY);
    }

    /*public Snake makeMouseControlledSnake(float headX, float headY, float momentumX, float momentumY, int turnLeftKey, int turnRightKey){
        MouseController ctrl= new MouseController();
        ctrl.setButton(turnLeftKey,2);
        ctrl.setButton(turnRightKey,1);
        return new Snake(ctrl, headX, headY, momentumX, momentumY);
    }*/
}
