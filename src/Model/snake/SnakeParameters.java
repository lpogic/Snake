package Model.snake;

public class SnakeParameters {

    private Snake snake;
    private float momentum;
    private float inclination;
    private int tailColor;
    private int headColor;
    private float momentumCurrent;
    private float inclinationCurrent;
    private int tailColorCurrent;
    private int headColorCurrent;


    public SnakeParameters(Snake snake, float momentum, float inclination, int tailColor, int headColor) {
        this.snake = snake;
        this.momentum = momentum;
        this.inclination = inclination;
        this.tailColor = tailColor;
        this.headColor = headColor;
    }

    public void reset(){
        momentumCurrent = momentum;
        inclinationCurrent = inclination;
        tailColorCurrent = tailColor;
        headColorCurrent = headColor;
    }

    public void load(){
        snake.getBody().getMomentum().setMag(momentumCurrent);
        snake.getBody().getInclination().setMag(inclinationCurrent);
        snake.getOutfit().setSegmentsColor(tailColorCurrent);
        snake.getOutfit().setHeadColor(headColorCurrent);
    }

    public float getMomentum() {
        return momentumCurrent;
    }

    public void setMomentum(float momentum) {
        this.momentumCurrent = momentum;
    }

    public float getInclination() {
        return inclinationCurrent;
    }

    public void setInclination(float inclination) {
        this.inclinationCurrent = inclination;
    }

    public int getTailColor() {
        return tailColorCurrent;
    }

    public void setTailColor(int tailColor) {
        this.tailColorCurrent = tailColor;
    }

    public int getHeadColor() {
        return headColorCurrent;
    }

    public void setHeadColor(int headColor) {
        this.headColorCurrent = headColor;
    }
}
