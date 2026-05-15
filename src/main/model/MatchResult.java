package main.model;

import main.model.enums.MatchType;
import java.util.List;

public class MatchResult {
    private List<Candy> matchedCandies;
    private MatchType matchType;
    private Candy spawnedCandy;

    public MatchResult(List<Candy> matchedCandies, MatchType matchType, Candy spawnedCandy) {
        this.matchedCandies = matchedCandies;
        this.matchType = matchType;
        this.spawnedCandy = spawnedCandy;
    }

    public List<Candy> getMatchedCandies() {
        return matchedCandies;
    }

    public void setMatchedCandies(List<Candy> matchedCandies) {
        this.matchedCandies = matchedCandies;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    public Candy getSpawnedCandy() {
        return spawnedCandy;
    }

    public void setSpawnedCandy(Candy spawnedCandy) {
        this.spawnedCandy = spawnedCandy;
    }
}
