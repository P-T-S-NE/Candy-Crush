package main.logic;

import main.GameManager;
import main.model.Board;

public class SelectionController {
    private GameManager gameManager;
    private Board board;
    private int selectedRow = -1;
    private int selectedCol = -1;

    public SelectionController(GameManager gameManager, Board board) {
        this.gameManager = gameManager;
        this.board = board;
    }

    public void selectCandy(int row, int col) {
        if (!gameManager.isIdle()) return;
        if (!board.isInside(row, col)) return;

        if (selectedRow == -1 && selectedCol == -1) {
            selectedRow = row;
            selectedCol = col;
        } else {
            if (Math.abs(selectedRow - row) + Math.abs(selectedCol - col) == 1) {
                gameManager.handleSwap(selectedRow, selectedCol, row, col);
            }
            selectedRow = -1;
            selectedCol = -1;
        }
        gameManager.requestRepaint();
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public int getSelectedCol() {
        return selectedCol;
    }
}
