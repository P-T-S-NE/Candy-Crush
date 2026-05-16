package main.logic;

import main.model.Board;
import main.model.Candy;
import main.model.CandyFactory;
import java.util.ArrayList;
import java.util.List;

public class BasicGravityLogic implements IGravityLogic {

    private CandyFactory candyFactory = CandyFactory.getInstance();

    public BasicGravityLogic() {
    }

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
        for (int c = 0; c < board.getCols(); c++) {
            for (int r = 0; r < board.getRows(); r++) {
                if (board.getCandy(r, c) == null) {
                    board.setCandy(r, c, candyFactory.createRandomCandy(r, c));
                }
            }
        }
    }

    @Override
    public List<Candy> generateNewCandies(Board board) {
        List<Candy> newCandies = new ArrayList<>();
        for (int c = 0; c < board.getCols(); c++) {
            int emptyCount = 0;
            for (int r = board.getRows() - 1; r >= 0; r--) {
                if (board.getCandy(r, c) == null) emptyCount++;
            }
            for (int r = 0; r < board.getRows(); r++) {
                if (board.getCandy(r, c) == null) {
                    Candy newCandy = candyFactory.createRandomCandy(r, c);
                    // Visual position is set higher than logical row based on empty count
                    // so it drops from above the board
                    newCandy.setVisualY(r - emptyCount);
                    newCandy.setVisualX(c);
                    board.setCandy(r, c, newCandy);
                    newCandies.add(newCandy);
                }
            }
        }
        return newCandies;
    }
}
