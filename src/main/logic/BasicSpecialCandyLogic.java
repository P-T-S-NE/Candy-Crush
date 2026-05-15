package main.logic;

import main.model.Board;
import main.model.Candy;

public class BasicSpecialCandyLogic implements ISpecialCandyLogic {

    @Override
    public void activateSpecialCandy(Board board, Candy candy) {
        int r = candy.getRow();
        int c = candy.getCol();
        switch (candy.getSpecialType()) {
            case STRIPED_HORIZONTAL:
                explodeRow(board, r);
                break;
            case STRIPED_VERTICAL:
                explodeColumn(board, c);
                break;
            case WRAPPED:
                explodeArea(board, r, c);
                break;
            default:
                break;
        }
    }

    private void explodeRow(Board board, int row) {
        for (int c = 0; c < board.getCols(); c++) {
            Candy target = board.getCandy(row, c);
            if (target != null) {
                board.setCandy(row, c, null);
                if (target.getSpecialType() != main.model.enums.SpecialType.NONE) {
                    activateSpecialCandy(board, target);
                }
            }
        }
    }

    private void explodeColumn(Board board, int col) {
        for (int r = 0; r < board.getRows(); r++) {
            Candy target = board.getCandy(r, col);
            if (target != null) {
                board.setCandy(r, col, null);
                if (target.getSpecialType() != main.model.enums.SpecialType.NONE) {
                    activateSpecialCandy(board, target);
                }
            }
        }
    }

    private void explodeArea(Board board, int row, int col) {
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if (board.isInside(r, c)) {
                    Candy target = board.getCandy(r, c);
                    if (target != null) {
                        board.setCandy(r, c, null);
                        if (target.getSpecialType() != main.model.enums.SpecialType.NONE) {
                            activateSpecialCandy(board, target);
                        }
                    }
                }
            }
        }
    }
}
