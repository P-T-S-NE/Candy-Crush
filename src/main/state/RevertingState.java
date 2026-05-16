package main.state;

import main.GameManager;

public class RevertingState implements IGameState {
    @Override
    public void update(GameManager manager) {
        manager.setState(manager.getIdleState());
    }
}
