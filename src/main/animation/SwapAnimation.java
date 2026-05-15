package main.animation;

import main.model.Candy;

public class SwapAnimation extends Animation {
    private Candy candy1;
    private Candy candy2;
    private float speed;

    public SwapAnimation(Candy candy1, Candy candy2, float speed) {
        this.candy1 = candy1;
        this.candy2 = candy2;
        this.speed = speed;
        this.finished = false;
    }

    @Override
    public void update() {
        // TODO: Implement swap animation logic
        this.finished = true;
    }
}
