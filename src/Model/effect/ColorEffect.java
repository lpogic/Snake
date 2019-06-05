package Model.effect;

import Model.snake.Snake;

public class ColorEffect extends TemporaryEffect {

    private int tailColor;
    private int headColor;

    public ColorEffect(long duration, int tailColor, int headColor) {
        super(duration);
        this.tailColor = tailColor;
        this.headColor = headColor;

    }

    @Override
    public void apply(Snake snake) {
        snake.getParameters().setTailColor(tailColor);
        snake.getParameters().setHeadColor(headColor);
    }
}
