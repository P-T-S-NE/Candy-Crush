package main.logic;

import main.model.Board;
import main.model.MatchResult;
import java.util.ArrayList;
import java.util.List;

public class BasicMatchLogic implements IMatchLogic {

    @Override
    public List<MatchResult> findMatches(Board board) {
        // TODO: Implement actual match finding logic
        return new ArrayList<>();
    }

    private void detectTShape() {
        // TODO: Implement T-shape detection
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private void detectLShape() {
        // TODO: Implement L-shape detection
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
