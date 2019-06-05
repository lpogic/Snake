package Alvic.manage;

import Alvic.react.Carnal;
import Alvic.react.Reactor;
import Alvic.print.Printer;
import Alvic.print.Visible;
import Alvic.update.Alive;
import Alvic.update.Updater;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class Manager {

    private Printer printer;
    private Updater updater;
    private Reactor reactor;
    private Deque<Runnable> maintenanceTasks;
    private int conjunction;
    private int cycle;
    private int printCycle;
    private int updateCycle;
    private int reactCycle;
    private boolean clear;

    public Manager(Printer printer, Updater updater, Reactor reactor,
                   int printCycle, int updateCycle, int reactCycle)
    {
        this.printer = printer;
        this.updater = updater;
        this.reactor = reactor;
        maintenanceTasks = new ArrayDeque<>();
        clear = false;
        cycle = 0;
        conjunction = 1;
        if(printCycle > 0)conjunction*= printCycle;
        if(updateCycle > 0)conjunction*= updateCycle;
        if(reactCycle > 0)conjunction*= reactCycle;
        this.printCycle = printCycle > 0 ? printCycle : conjunction+1;
        this.updateCycle = updateCycle > 0 ? updateCycle : conjunction+1;
        this.reactCycle = reactCycle > 0 ? reactCycle : conjunction+1;
    }

    public Manager(int printCycle, int updateCycle, int reactCycle) {
        this(new Printer(0x00888888), new Updater(), new Reactor(),
                printCycle, updateCycle, reactCycle);
    }

    public Printer getPrinter() {
        return printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public Updater getUpdater() {
        return updater;
    }

    public void setUpdater(Updater updater) {
        this.updater = updater;
    }

    public Reactor getReactor() {
        return reactor;
    }

    public void setReactor(Reactor reactor) {
        this.reactor = reactor;
    }

    public void step(){
        maintenance();
        if(cycle % updateCycle == 0)updater.update();
        if(cycle % printCycle == 0)printer.print();
        if(cycle % reactCycle == 0)reactor.react();
        if(++cycle >= conjunction)cycle = 0;
    }

    private void maintenance()
    {
        if(clear){
            printer.clear();
            updater.clear();
            reactor.clear();
            clear = false;
        }
        else maintenanceTasks.forEach(Runnable::run);
        maintenanceTasks.clear();
    }

    public void addAlive(Object o){
        if(o instanceof Alive){
            maintenanceTasks.add(()->updater.add((Alive)o));
        }
    }

    public void addVisible(Object o){
        if(o instanceof Visible){
            maintenanceTasks.add(()->printer.add((Visible)o));
        }
    }

    public void addVisible(Object o, int layer){
        if(o instanceof Visible){
            maintenanceTasks.add(()->printer.add((Visible)o, layer));
        }
    }

    public void addCarnal(Object o){
        if(o instanceof Carnal){
            maintenanceTasks.add(()->reactor.add((Carnal)o));
        }
    }

    public void add(Object o){
        addAlive(o);
        addVisible(o);
        addCarnal(o);
    }

    public void add(Object o, int printLayer){
        addAlive(o);
        addVisible(o, printLayer);
        addCarnal(o);
    }

    public void add(Object ... array){
        for(Object it: array) add(it);
    }

    public void add(Collection<?> collection){
        for (Object e : collection) add(e);
    }

    public void removeAlive(Object o){
        if(o instanceof Alive){
            maintenanceTasks.add(()->updater.remove((Alive)o));
        }
    }

    public void removeVisible(Object o){
        if(o instanceof Visible){
            maintenanceTasks.add(()->printer.remove((Visible)o));
        }
    }

    public void removeCarnal(Object o){
        if(o instanceof Carnal){
            maintenanceTasks.add(()->reactor.remove((Carnal)o));
        }
    }

    public void remove(Object o){
        removeAlive(o);
        removeVisible(o);
        removeCarnal(o);
    }

    public void remove(Object ... array){
        for(Object it : array) remove(it);
    }

    public void remove(Collection<?> collection){
        for(Object it : collection) remove(it);
    }

    public void renew(Object o){
        remove(o);
        add(o);
    }

    public void clear(){
        clear = true;
    }

    public int size(){
        return printer.size() + updater.size() + reactor.size();
    }
}