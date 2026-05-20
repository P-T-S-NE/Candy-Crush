package main.logic.effects;

import main.model.Board;
import main.model.Candy;
import main.model.enums.SpecialType;
import main.logic.ISpecialCandyLogic;
import main.logic.ICandyDestroyListener;
import java.util.EnumMap;
import java.util.Map;

public class SpecialEffectRegistry {
    private static SpecialEffectRegistry instance;
    private final Map<SpecialType, ISpecialEffectStrategy> strategies = new EnumMap<>(SpecialType.class);

    private SpecialEffectRegistry() {
        strategies.put(SpecialType.STRIPED_HORIZONTAL, (board, candy, logic, listener) -> {
            int row = candy.getRow();
            for (int c = 0; c < board.getCols(); c++) {
                Candy target = board.getCandy(row, c);
                if (target != null) {
                    board.setCandy(row, c, null);
                    listener.onCandyDestroyed(target);
                    if (target.getSpecialType() != SpecialType.NONE) {
                        logic.activateSpecialCandy(board, target, listener);
                    }
                }
            }
        });

        strategies.put(SpecialType.STRIPED_VERTICAL, (board, candy, logic, listener) -> {
            int col = candy.getCol();
            for (int r = 0; r < board.getRows(); r++) {
                Candy target = board.getCandy(r, col);
                if (target != null) {
                    board.setCandy(r, col, null);
                    listener.onCandyDestroyed(target);
                    if (target.getSpecialType() != SpecialType.NONE) {
                        logic.activateSpecialCandy(board, target, listener);
                    }
                }
            }
        });

        strategies.put(SpecialType.WRAPPED, (board, candy, logic, listener) -> {
            int row = candy.getRow();
            int col = candy.getCol();
            for (int r = row - 1; r <= row + 1; r++) {
                for (int c = col - 1; c <= col + 1; c++) {
                    if (board.isInside(r, c)) {
                        Candy target = board.getCandy(r, c);
                        if (target != null) {
                            board.setCandy(r, c, null);
                            listener.onCandyDestroyed(target);
                            if (target.getSpecialType() != SpecialType.NONE) {
                                logic.activateSpecialCandy(board, target, listener);
                            }
                        }
                    }
                }
            }
        });
    }

    public static SpecialEffectRegistry getInstance() {
        if (instance == null) {
            instance = new SpecialEffectRegistry();
        }
        return instance;
    }

    public ISpecialEffectStrategy getStrategy(SpecialType type) {
        return strategies.get(type);
    }
    
    public void registerStrategy(SpecialType type, ISpecialEffectStrategy strategy) {
        strategies.put(type, strategy);
    }
}
