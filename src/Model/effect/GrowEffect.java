package Model.effect;

import Model.snake.Snake;

public class GrowEffect implements Effect {
    private int segmentsToGrow;

    public GrowEffect(int segmentsToGrow) {
        this.segmentsToGrow = segmentsToGrow;
    }

    @Override
    public void apply(Snake snake) {
        for(int i = 0;i < segmentsToGrow;++i) {
            snake.getBody().getTail().enlarge();
        }
    }

    @Override
    public boolean live() {
        return false;
    }
}
