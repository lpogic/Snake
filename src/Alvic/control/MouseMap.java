package Alvic.control;

import processing.core.PApplet;
import processing.core.PVector;

public class MouseMap
{
    private PVector[] pressPoint;
    private PVector[] releasePoint;
    private boolean[] buttonDown;

    public MouseMap() {
        pressPoint = new PVector[3];
        releasePoint = new PVector[3];
        buttonDown = new boolean[3];
        for (int i = 0; i < 3; ++i) {
            pressPoint[i] = new PVector();
            releasePoint[i] = new PVector();
        }
    }

    private int buttonToIndex(int button) {
        switch (button) {
            case PApplet.RIGHT:
                return 0;
            case PApplet.LEFT:
                return 1;
            case PApplet.CENTER:
                return 2;
        }
        return 0;
    }

    public void setButton(float x, float y, int button) {
        int i = buttonToIndex(button);
        pressPoint[i].set(x, y);
        buttonDown[i] = true;
    }

    public void resetButton(float x, float y, int button) {
        int i = buttonToIndex(button);
        releasePoint[i].set(x, y);
        buttonDown[i] = false;
    }

    public boolean isTyped(int button) {
        return buttonDown[buttonToIndex(button)];
    }

    public PVector getPressPoint(int button) {
        return pressPoint[buttonToIndex(button)];
    }

    public PVector getReleasePoint(int button) {
        return releasePoint[buttonToIndex(button)];
    }
}
