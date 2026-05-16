package main;

import main.model.Board;
import main.model.Candy;
import main.model.MatchResult;
import main.model.ScoreManager;
import main.logic.IMatchLogic;
import main.logic.IGravityLogic;
import main.logic.ISpecialCandyLogic;
import main.logic.BasicMatchLogic;
import main.logic.BasicGravityLogic;
import main.logic.BasicSpecialCandyLogic;
import main.animation.AnimationSystem;
import main.animation.SwapAnimation;
import main.animation.FallAnimation;
import javax.swing.Timer;
import java.util.List;

public class GameManager {
    public enum GameState {
        IDLE, SWAPPING, REVERTING, MATCHING, FALLING
    }

    private Board board;
    private IMatchLogic matchLogic;
    private IGravityLogic gravityLogic;
    private ISpecialCandyLogic specialCandyLogic;
    private AnimationSystem animationSystem;
    private ScoreManager scoreManager;
    private Runnable repaintCallback;
    private GameState state = GameState.IDLE;
    private Timer gameLoop;
    
    private int swapR1, swapC1, swapR2, swapC2;

    public GameManager(IMatchLogic matchLogic, IGravityLogic gravityLogic, ISpecialCandyLogic specialCandyLogic, ScoreManager scoreManager) {
        this.board = new Board(8, 8);
        this.matchLogic = matchLogic;
        this.gravityLogic = gravityLogic;
        this.specialCandyLogic = specialCandyLogic;
        this.scoreManager = scoreManager;
        this.animationSystem = new AnimationSystem();
    }

    public void setRepaintCallback(Runnable repaintCallback) {
        this.repaintCallback = repaintCallback;
    }

    public void start() {
        gravityLogic.refill(board);
        List<MatchResult> initialMatches = matchLogic.findMatches(board);
        while (!initialMatches.isEmpty()) {
            for (int r = 0; r < board.getRows(); r++) {
                for (int c = 0; c < board.getCols(); c++) {
                    board.setCandy(r, c, null);
                }
            }
            gravityLogic.refill(board);
            initialMatches = matchLogic.findMatches(board);
        }
        
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {
                Candy candy = board.getCandy(r, c);
                if (candy != null) {
                    candy.setVisualX(c);
                    candy.setVisualY(r);
                }
            }
        }
        
        if (repaintCallback != null) repaintCallback.run();

        gameLoop = new Timer(16, e -> update());
        gameLoop.start();
    }

    public void update() {
        if (animationSystem.isAnimating()) {
            animationSystem.update();
            if (repaintCallback != null) repaintCallback.run();
            return;
        }

        switch (state) {
            case SWAPPING:
                List<MatchResult> matches = matchLogic.findMatches(board);
                if (matches.isEmpty()) {
                    board.swap(swapR1, swapC1, swapR2, swapC2);
                    Candy c1 = board.getCandy(swapR1, swapC1);
                    Candy c2 = board.getCandy(swapR2, swapC2);
                    animationSystem.addAnimation(new SwapAnimation(c1, c2, 0.2f));
                    state = GameState.REVERTING;
                } else {
                    state = GameState.MATCHING;
                }
                break;
            case REVERTING:
                state = GameState.IDLE;
                break;
            case MATCHING:
                List<MatchResult> currentMatches = matchLogic.findMatches(board);
                if (!currentMatches.isEmpty()) {
                    processMatches(currentMatches);
                    state = GameState.FALLING;
                    refillAndAnimate();
                } else {
                    state = GameState.IDLE;
                }
                break;
            case FALLING:
                state = GameState.MATCHING;
                break;
            case IDLE:
                break;
        }
        if (repaintCallback != null) repaintCallback.run();
    }

    private void refillAndAnimate() {
        gravityLogic.applyGravity(board);
        
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {
                Candy candy = board.getCandy(r, c);
                if (candy != null && candy.getVisualY() < r) {
                    animationSystem.addAnimation(new FallAnimation(candy, r, 0.2f));
                }
            }
        }
        
        List<Candy> newCandies = gravityLogic.generateNewCandies(board);
        for (Candy newCandy : newCandies) {
            animationSystem.addAnimation(new FallAnimation(newCandy, newCandy.getRow(), 0.2f));
        }
    }

    public void requestRepaint() {
        if (repaintCallback != null) repaintCallback.run();
    }

    public boolean isIdle() {
        return state == GameState.IDLE;
    }

    public void handleSwap(int r1, int c1, int r2, int c2) {
        this.swapR1 = r1;
        this.swapC1 = c1;
        this.swapR2 = r2;
        this.swapC2 = c2;

        Candy c1Obj = board.getCandy(r1, c1);
        Candy c2Obj = board.getCandy(r2, c2);

        board.swap(r1, c1, r2, c2);
        animationSystem.addAnimation(new SwapAnimation(c1Obj, c2Obj, 0.2f));
        state = GameState.SWAPPING;
    }

    public void processMatches(List<MatchResult> matches) {
        for (MatchResult match : matches) {
            for (Candy candy : match.getMatchedCandies()) {
                if (candy != null && board.getCandy(candy.getRow(), candy.getCol()) != null) {
                    scoreManager.addScore(10);
                    board.setCandy(candy.getRow(), candy.getCol(), null);
                    if (candy.getSpecialType() != main.model.enums.SpecialType.NONE) {
                        specialCandyLogic.activateSpecialCandy(board, candy);
                    }
                }
            }
            Candy spawned = match.getSpawnedCandy();
            if (spawned != null) {
                spawned.setVisualX(spawned.getCol());
                spawned.setVisualY(spawned.getRow());
                board.setCandy(spawned.getRow(), spawned.getCol(), spawned);
            }
        }
    }

    public Board getBoard() { return board; }
}
