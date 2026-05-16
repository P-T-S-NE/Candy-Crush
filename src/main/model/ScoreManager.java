package main.model;

public class ScoreManager {
    private static ScoreManager instance;
    private int score = 0;

    private ScoreManager() {}

    public static ScoreManager getInstance() {
        if (instance == null) {
            instance = new ScoreManager();
        }
        return instance;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public int getScore() {
        return score;
    }

    public void reset() {
        this.score = 0;
    }
}
