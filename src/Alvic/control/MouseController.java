package Alvic.control;

import Alvic.Apk;
import processing.core.PVector;

import java.util.HashMap;
import java.util.Map;

public class MouseController implements Controller
{
    private Map<Object,Integer> buttons;
    private Map<Object, PVector> startPoints;
    private Map<Object,PVector> endPoints;

    public MouseController() {
        buttons = new HashMap<>();
        startPoints = new HashMap<>();
        endPoints = new HashMap<>();
    }

    public void setButton(int button, Object reference) {
        buttons.put(reference, button);
    }

    public void removeButton(Object reference) {
        buttons.remove(reference);
        startPoints.remove(reference);
        endPoints.remove(reference);
    }

    public void setVector(PVector startPoint, PVector endPoint, Object reference) {
        startPoints.put(reference, startPoint);
        endPoints.put(reference, endPoint);
    }

    public void setVector(PVector startPoint, Object reference) {
        setVector(startPoint,null,reference);
    }

    public PVector getVector(Object reference) {
        return PVector.sub(startPoints.getOrDefault(reference,currentMousePosition()),
                endPoints.getOrDefault(reference,currentMousePosition()));
    }

    public PVector currentMousePosition(){
        return new PVector(Apk.applet.mouseX,Apk.applet.mouseY);
    }

    public boolean induced(Object reference) {
        Integer button = buttons.get(reference);
        if (button == null) return false;
        return Apk.mouse.isTyped(button);
    }
}
