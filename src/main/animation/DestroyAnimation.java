package main.animation;

import main.model.Candy;

public class DestroyAnimation extends Animation {
    private Candy candy;
    private float fadeSpeed;

    public DestroyAnimation(Candy candy, float fadeSpeed) {
        this.candy = candy;
        this.fadeSpeed = fadeSpeed;
        this.finished = false;
    }

    @Override
    public void update() {
        // TODO: Implement destroy animation logic
        this.finished = true;
    }
}
