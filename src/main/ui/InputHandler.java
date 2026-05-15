package main.ui;

import main.GameManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InputHandler extends MouseAdapter {
    private GameManager gameManager;

    public InputHandler(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO: Map mouse coordinates to grid coordinates
        // Notify gameManager of the interaction
    }
}
