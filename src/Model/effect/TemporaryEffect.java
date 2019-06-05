package Model.effect;

public abstract class TemporaryEffect implements Effect {

    private long endMoment;

    public TemporaryEffect(long duration) {
        this.endMoment = System.currentTimeMillis() + duration;
    }

    @Override
    public boolean live() {
        return System.currentTimeMillis() < endMoment;
    }
}
