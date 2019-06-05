package Model.effect;

import Model.snake.Snake;

public interface Effect {
    void apply(Snake snake);
    boolean live();
}
