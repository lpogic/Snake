package Alvic.control;

import Alvic.Apk;

import java.util.HashMap;
import java.util.Map;

public class KeyboardController implements Controller
{
    private Map<Object,Integer> keys;

    public KeyboardController() {
        keys = new HashMap<>();
    }

    public void setKey(int key, Object reference) {
        keys.put(reference, key);
    }

    public void removeKey(Object reference) {
        keys.remove(reference);
    }

    public boolean induced(Object reference) {
        Integer key = keys.get(reference);
        if (key == null) return false;
        return Apk.keyboard.isTyped(key);
    }

    /*public Object least(){
        while(Apk.keyboard.isScribed()){
            Integer
        }
    }*/
}
