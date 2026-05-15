package main.logic;

import main.model.Board;

public interface IGravityLogic {
    void applyGravity(Board board);
    void refill(Board board);
}
