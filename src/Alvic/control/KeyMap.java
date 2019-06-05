package Alvic.control;

import java.util.*;

public class KeyMap
{
    private Map<Integer,Boolean> keys;
    private Deque<Integer> scribe;
    private int scribeLimit;

    public KeyMap() {
        keys = new HashMap<>();
        scribeLimit = 10;
        scribe = new LinkedList<>();
    }

    public void setKey(int key) {
        keys.put(key, true);
        scribe.addLast(key);
        if(scribe.size() > scribeLimit)scribe.removeFirst();
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

    public boolean isScribed(){
        return scribe.size() > 0;
    }

    public void clearScribe(){
        scribe.clear();
    }

    public Integer getScribed(){
        return scribe.removeFirst();
    }
}
