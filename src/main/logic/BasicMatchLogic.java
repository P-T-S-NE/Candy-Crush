package main.logic;

import main.model.Board;
import main.model.Candy;
import main.model.CandyFactory;
import main.model.MatchResult;
import main.model.enums.MatchType;
import main.model.enums.SpecialType;
import java.util.ArrayList;
import java.util.List;

public class BasicMatchLogic implements IMatchLogic {

    private CandyFactory candyFactory = CandyFactory.getInstance();

    public BasicMatchLogic() {
    }

    @Override
    public List<MatchResult> findMatches(Board board) {
        List<MatchResult> allMatches = new ArrayList<>();

        // Horizontal
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols() - 2; c++) {
                Candy current = board.getCandy(r, c);
                if (current == null)
                    continue;

                int matchLength = 1;
                while (c + matchLength < board.getCols() &&
                        board.getCandy(r, c + matchLength) != null &&
                        board.getCandy(r, c + matchLength).getType() == current.getType()) {
                    matchLength++;
                }

                if (matchLength >= 3) {
                    List<Candy> matchedCandies = new ArrayList<>();
                    for (int i = 0; i < matchLength; i++) {
                        matchedCandies.add(board.getCandy(r, c + i));
                    }

                    Candy spawned = null;
                    MatchType matchType = MatchType.THREE;

                    if (matchLength == 4) {
                        matchType = MatchType.FOUR_HORIZONTAL;
                        spawned = candyFactory.createSpecialCandy(current.getType(), SpecialType.STRIPED_VERTICAL, r, c + 1);
                    } else if (matchLength >= 5) {
                        matchType = MatchType.FIVE;
                        spawned = candyFactory.createSpecialCandy(current.getType(), SpecialType.WRAPPED, r, c + 2);
                    }

                    allMatches.add(new MatchResult(matchedCandies, matchType, spawned));
                    c += matchLength - 1;
                }
            }
        }

        // Vertical
        for (int c = 0; c < board.getCols(); c++) {
            for (int r = 0; r < board.getRows() - 2; r++) {
                Candy current = board.getCandy(r, c);
                if (current == null)
                    continue;

                int matchLength = 1;
                while (r + matchLength < board.getRows() &&
                        board.getCandy(r + matchLength, c) != null &&
                        board.getCandy(r + matchLength, c).getType() == current.getType()) {
                    matchLength++;
                }

                if (matchLength >= 3) {
                    List<Candy> matchedCandies = new ArrayList<>();
                    for (int i = 0; i < matchLength; i++) {
                        matchedCandies.add(board.getCandy(r + i, c));
                    }

                    Candy spawned = null;
                    MatchType matchType = MatchType.THREE;

                    if (matchLength == 4) {
                        matchType = MatchType.FOUR_VERTICAL;
                        spawned = candyFactory.createSpecialCandy(current.getType(), SpecialType.STRIPED_HORIZONTAL, r + 1, c);
                    } else if (matchLength >= 5) {
                        matchType = MatchType.FIVE;
                        spawned = candyFactory.createSpecialCandy(current.getType(), SpecialType.WRAPPED, r + 2, c);
                    }

                    allMatches.add(new MatchResult(matchedCandies, matchType, spawned));
                    r += matchLength - 1;
                }
            }
        }

        return allMatches;
    }
}
