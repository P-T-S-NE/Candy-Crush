package main.logic;

import main.model.Board;
import main.model.MatchResult;
import java.util.List;

public interface IMatchLogic {
    List<MatchResult> findMatches(Board board);
}
