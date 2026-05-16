package main.logic.effects;

import main.model.Board;
import main.model.Candy;
import main.logic.ISpecialCandyLogic;

public interface ISpecialEffectStrategy {
    void execute(Board board, Candy candy, ISpecialCandyLogic logic);
}
