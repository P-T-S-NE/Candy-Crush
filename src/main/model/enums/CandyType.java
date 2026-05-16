package main.model.enums;

import java.awt.Color;

public enum CandyType {
    RED(new Color(255, 80, 80)),
    BLUE(new Color(80, 180, 255)),
    GREEN(new Color(80, 255, 80)),
    YELLOW(new Color(255, 255, 80)),
    PURPLE(new Color(200, 80, 255)),
    ORANGE(new Color(255, 150, 50));

    private final Color color;

    CandyType(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
