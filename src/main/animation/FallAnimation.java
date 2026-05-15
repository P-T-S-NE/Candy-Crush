package main.animation;

import main.model.Candy;

public class FallAnimation extends Animation {
    private Candy candy;
    private float targetY;
    private float speed;

    public FallAnimation(Candy candy, float targetY, float speed) {
        this.candy = candy;
        this.targetY = targetY;
        this.speed = speed;
        this.finished = false;
    }

    @Override
    public void update() {
        // TODO: Implement fall animation logic
        this.finished = true;
    }
}
