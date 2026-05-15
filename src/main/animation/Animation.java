package main.animation;

public abstract class Animation {
    protected boolean finished;

    public abstract void update();

    public boolean isFinished() {
        return finished;
    }
}
