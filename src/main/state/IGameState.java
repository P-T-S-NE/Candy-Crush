package main.state;

import main.GameManager;

public interface IGameState {
    void update(GameManager manager);
}
