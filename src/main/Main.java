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
            // Assuming GameManager sets up GamePanel inside or we pass it
            GamePanel panel = new GamePanel(gameManager);
            
            frame.add(panel);
            frame.setSize(800, 600); // Default size
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
            // start the game
            try {
                gameManager.start();
            } catch (UnsupportedOperationException e) {
                System.out.println("GameManager.start() is not implemented yet. Stubs generated successfully.");
            }
        });
    }
}
