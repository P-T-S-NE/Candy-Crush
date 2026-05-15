package main.animation;

import main.model.Candy;

public class SwapAnimation extends Animation {
    private Candy candy1;
    private Candy candy2;
    private float target1X, target1Y;
    private float target2X, target2Y;
    private float speed;

    public SwapAnimation(Candy candy1, Candy candy2, float speed) {
        this.candy1 = candy1;
        this.candy2 = candy2;
        this.target1X = candy1.getCol();
        this.target1Y = candy1.getRow();
        this.target2X = candy2.getCol();
        this.target2Y = candy2.getRow();
        this.speed = speed;
        this.finished = false;
    }

    @Override
    public void update() {
        boolean c1Done = moveCandy(candy1, target1X, target1Y);
        boolean c2Done = moveCandy(candy2, target2X, target2Y);
        if (c1Done && c2Done) {
            this.finished = true;
        }
    }

    private boolean moveCandy(Candy c, float targetX, float targetY) {
        float dx = targetX - c.getVisualX();
        float dy = targetY - c.getVisualY();
        float dist = (float) Math.sqrt(dx*dx + dy*dy);
        
        if (dist <= speed) {
            c.setVisualX(targetX);
            c.setVisualY(targetY);
            return true;
        } else {
            c.setVisualX(c.getVisualX() + (dx / dist) * speed);
            c.setVisualY(c.getVisualY() + (dy / dist) * speed);
            return false;
        }
    }
}
