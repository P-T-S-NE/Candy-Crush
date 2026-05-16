package main.state;

import main.GameManager;
import main.model.Candy;
import main.model.MatchResult;
import main.animation.SwapAnimation;
import java.util.List;

public class SwappingState implements IGameState {
    @Override
    public void update(GameManager manager) {
        List<MatchResult> matches = manager.getMatchLogic().findMatches(manager.getBoard());
        if (matches.isEmpty()) {
            manager.getBoard().swap(manager.getSwapR1(), manager.getSwapC1(), manager.getSwapR2(), manager.getSwapC2());
            Candy c1 = manager.getBoard().getCandy(manager.getSwapR1(), manager.getSwapC1());
            Candy c2 = manager.getBoard().getCandy(manager.getSwapR2(), manager.getSwapC2());
            manager.getAnimationSystem().addAnimation(new SwapAnimation(c1, c2, 0.2f));
            manager.setState(manager.getRevertingState());
        } else {
            manager.setState(manager.getMatchingState());
        }
    }
}
