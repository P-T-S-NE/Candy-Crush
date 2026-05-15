package main.ui;

import main.GameManager;
import javax.swing.JPanel;
import java.awt.Graphics;

public class GamePanel extends JPanel {
    private GameManager gameManager;

    public GamePanel(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard();
        drawCandies();
        drawSelector();
        drawScore();
    }

    private void drawBoard() {
        // TODO: Implement drawing the board background
    }

    private void drawCandies() {
        // TODO: Implement drawing candies
    }

    private void drawSelector() {
        // TODO: Implement drawing the current selection/cursor
    }

    private void drawScore() {
        // TODO: Implement drawing the score
    }
}
