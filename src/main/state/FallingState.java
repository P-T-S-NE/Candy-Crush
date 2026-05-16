package main.state;

import main.GameManager;

public class FallingState implements IGameState {
    @Override
    public void update(GameManager manager) {
        manager.setState(manager.getMatchingState());
    }
}
