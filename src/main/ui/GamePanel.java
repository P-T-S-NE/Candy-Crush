package main.ui;

import main.GameManager;
import main.model.Board;
import main.model.Candy;
import main.model.ScoreManager;
import main.logic.SelectionController;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

public class GamePanel extends JPanel {
    private GameManager gameManager;
    private ScoreManager scoreManager = ScoreManager.getInstance();
    private SelectionController selectionController;
    public static final int CELL_SIZE = 60;
    public static final int OFFSET_X = 50;
    public static final int OFFSET_Y = 50;

    public GamePanel(GameManager gameManager, SelectionController selectionController) {
        this.gameManager = gameManager;
        this.selectionController = selectionController;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(40, 40, 40));
        drawBoard(g);
        drawCandies(g);
        drawSelector(g);
        drawScore(g);
    }

    private void drawBoard(Graphics g) {
        Board board = gameManager.getBoard();
        if (board == null)
            return;

        g.setColor(new Color(60, 60, 60));
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {
                g.fillRect(OFFSET_X + c * CELL_SIZE, OFFSET_Y + r * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(new Color(80, 80, 80));
                g.drawRect(OFFSET_X + c * CELL_SIZE, OFFSET_Y + r * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    private void drawCandies(Graphics g) {
        Board board = gameManager.getBoard();
        if (board == null)
            return;

        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {
                Candy candy = board.getCandy(r, c);
                if (candy != null) {
                    drawCandy(g, candy);
                }
            }
        }
    }

    private void drawCandy(Graphics g, Candy candy) {
        int x = OFFSET_X + (int) (candy.getVisualX() * CELL_SIZE);
        int y = OFFSET_Y + (int) (candy.getVisualY() * CELL_SIZE);
        int padding = 8;

        g.setColor(candy.getType().getColor());

        g.fillOval(x + padding, y + padding, CELL_SIZE - 2 * padding, CELL_SIZE - 2 * padding);

        // Reflection for glossy look
        g.setColor(new Color(255, 255, 255, 100));
        g.fillOval(x + padding + 5, y + padding + 5, 15, 10);

        main.ui.renderer.SpecialCandyRendererRegistry.getInstance().render(candy.getSpecialType(), g, x, y, CELL_SIZE, padding);
    }

    private void drawSelector(Graphics g) {
        int r = selectionController.getSelectedRow();
        int c = selectionController.getSelectedCol();
        if (r != -1 && c != -1) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawRect(OFFSET_X + c * CELL_SIZE, OFFSET_Y + r * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            g2d.setStroke(new BasicStroke(1));
        }
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Score: " + scoreManager.getScore(), 10, 20);
    }
}
