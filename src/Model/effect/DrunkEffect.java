package Model.effect;

import Model.snake.Snake;

public class DrunkEffect extends TemporaryEffect {

    private float level;

    public DrunkEffect(long duration, double level) {
        super(duration);
        this.level = (float)level;
    }

    @Override
    public void apply(Snake snake) {
        snake.getParameters().setInclination(snake.getParameters().getInclination() + level);
    }
}
