package main.state;

import main.GameManager;
import main.model.MatchResult;
import java.util.List;

public class MatchingState implements IGameState {
    @Override
    public void update(GameManager manager) {
        List<MatchResult> currentMatches = manager.getMatchLogic().findMatches(manager.getBoard());
        if (!currentMatches.isEmpty()) {
            manager.processMatches(currentMatches);
            manager.setState(manager.getFallingState());
            manager.refillAndAnimate();
        } else {
            manager.setState(manager.getIdleState());
        }
    }
}
