package main.logic;

import main.model.Board;
import main.model.Candy;
import java.util.List;

public interface IGravityLogic {
    void applyGravity(Board board);
    void refill(Board board);
    List<Candy> generateNewCandies(Board board);
}
