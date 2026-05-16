package main.model;

import main.model.enums.CandyType;
import main.model.enums.SpecialType;

public class CandyFactory {
    
    public Candy createRandomCandy(int row, int col) {
        CandyType[] types = CandyType.values();
        CandyType randomType = types[(int)(Math.random() * types.length)];
        return new Candy(randomType, SpecialType.NONE, row, col);
    }

    public Candy createSpecialCandy(CandyType type, SpecialType specialType, int row, int col) {
        return new Candy(type, specialType, row, col);
    }
}
