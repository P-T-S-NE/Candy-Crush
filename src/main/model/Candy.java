package main.model;

import main.model.enums.CandyType;
import main.model.enums.SpecialType;

public class Candy {
    private CandyType type;
    private SpecialType specialType;
    private int row;
    private int col;
    private float visualX;
    private float visualY;
    private float alpha;
    private float scale;
    private boolean falling;
    private boolean destroying;

    public Candy(CandyType type, SpecialType specialType, int row, int col) {
        this.type = type;
        this.specialType = specialType;
        this.row = row;
        this.col = col;
        this.visualX = col; // Assuming basic initial coordinate maps to grid position
        this.visualY = row;
        this.alpha = 1.0f;
        this.scale = 1.0f;
        this.falling = false;
        this.destroying = false;
    }

    public CandyType getType() { return type; }
    public void setType(CandyType type) { this.type = type; }

    public SpecialType getSpecialType() { return specialType; }
    public void setSpecialType(SpecialType specialType) { this.specialType = specialType; }

    public int getRow() { return row; }
    public void setRow(int row) { this.row = row; }

    public int getCol() { return col; }
    public void setCol(int col) { this.col = col; }

    public float getVisualX() { return visualX; }
    public void setVisualX(float visualX) { this.visualX = visualX; }

    public float getVisualY() { return visualY; }
    public void setVisualY(float visualY) { this.visualY = visualY; }

    public float getAlpha() { return alpha; }
    public void setAlpha(float alpha) { this.alpha = alpha; }

    public float getScale() { return scale; }
    public void setScale(float scale) { this.scale = scale; }

    public boolean isFalling() { return falling; }
    public void setFalling(boolean falling) { this.falling = falling; }

    public boolean isDestroying() { return destroying; }
    public void setDestroying(boolean destroying) { this.destroying = destroying; }
}
