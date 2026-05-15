package main.logic;

import main.model.Board;

public class BasicGravityLogic implements IGravityLogic {

    @Override
    public void applyGravity(Board board) {
        for (int c = 0; c < board.getCols(); c++) {
            for (int r = board.getRows() - 1; r >= 0; r--) {
                if (board.getCandy(r, c) == null) {
                    for (int k = r - 1; k >= 0; k--) {
                        if (board.getCandy(k, c) != null) {
                            board.swap(r, c, k, c);
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void refill(Board board) {
        main.model.enums.CandyType[] types = main.model.enums.CandyType.values();
        for (int c = 0; c < board.getCols(); c++) {
            for (int r = 0; r < board.getRows(); r++) {
                if (board.getCandy(r, c) == null) {
                    main.model.enums.CandyType randomType = types[(int)(Math.random() * types.length)];
                    board.setCandy(r, c, new main.model.Candy(randomType, main.model.enums.SpecialType.NONE, r, c));
                }
            }
        }
    }
}
