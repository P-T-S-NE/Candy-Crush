package main.model;

import main.model.enums.CandyType;
import main.model.enums.SpecialType;

public class CandyFactory {
    private static CandyFactory instance;

    private CandyFactory() {}

    public static CandyFactory getInstance() {
        if (instance == null) {
            instance = new CandyFactory();
        }
        return instance;
    }
    
    public Candy createRandomCandy(int row, int col) {
        CandyType[] types = CandyType.values();
        CandyType randomType = types[(int)(Math.random() * types.length)];
        return new Candy(randomType, SpecialType.NONE, row, col);
    }

    public Candy createSpecialCandy(CandyType type, SpecialType specialType, int row, int col) {
        return new Candy(type, specialType, row, col);
    }
}
