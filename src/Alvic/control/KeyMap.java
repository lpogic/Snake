package Alvic.control;

import java.util.HashMap;
import java.util.Map;

public class KeyMap
{
    private Map<Integer,Boolean> keys;

    public KeyMap() {
        keys = new HashMap<>();
    }

    public void setKey(int key) {
        keys.put(key, true);
    }

    public void resetKey(int key) {
        keys.put(key, false);
    }

    public boolean isTyped(int key) {
        return keys.getOrDefault(key,false);
    }

    public Map<Integer,Boolean> getMap(){
        return keys;
    }
}
