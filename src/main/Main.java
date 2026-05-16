package main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import main.ui.GamePanel;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Candy Crush");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            main.model.CandyFactory candyFactory = new main.model.CandyFactory();
            main.logic.IMatchLogic matchLogic = new main.logic.BasicMatchLogic(candyFactory);
            main.logic.IGravityLogic gravityLogic = new main.logic.BasicGravityLogic(candyFactory);
            main.logic.ISpecialCandyLogic specialCandyLogic = new main.logic.BasicSpecialCandyLogic();

            main.model.ScoreManager scoreManager = new main.model.ScoreManager();
            GameManager gameManager = new GameManager(matchLogic, gravityLogic, specialCandyLogic, scoreManager);
            main.logic.SelectionController selectionController = new main.logic.SelectionController(gameManager,
                    gameManager.getBoard());

            GamePanel panel = new GamePanel(gameManager, scoreManager, selectionController);
            gameManager.setRepaintCallback(() -> panel.repaint());

            panel.addMouseListener(new main.ui.InputHandler(selectionController));

            frame.add(panel);
            frame.setSize(600, 650);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            try {
                gameManager.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
