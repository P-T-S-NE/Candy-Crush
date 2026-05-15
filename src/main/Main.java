package main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import main.ui.GamePanel;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Candy Crush");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            GameManager gameManager = new GameManager();
            GamePanel panel = new GamePanel(gameManager);
            gameManager.setGamePanel(panel);

            panel.addMouseListener(new main.ui.InputHandler(gameManager));

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
