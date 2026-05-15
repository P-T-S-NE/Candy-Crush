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
        int col = (e.getX() - GamePanel.OFFSET_X) / GamePanel.CELL_SIZE;
        int row = (e.getY() - GamePanel.OFFSET_Y) / GamePanel.CELL_SIZE;
        
        gameManager.selectCandy(row, col);
    }
}
