package main.model;

public class Board {
    private Candy[][] grid;
    private int rows;
    private int cols;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Candy[rows][cols];
    }

    public void swap(int row1, int col1, int row2, int col2) {
        if (isInside(row1, col1) && isInside(row2, col2)) {
            Candy temp = grid[row1][col1];
            grid[row1][col1] = grid[row2][col2];
            grid[row2][col2] = temp;
            
            // Update candy logical positions
            if (grid[row1][col1] != null) {
                grid[row1][col1].setRow(row1);
                grid[row1][col1].setCol(col1);
            }
            if (grid[row2][col2] != null) {
                grid[row2][col2].setRow(row2);
                grid[row2][col2].setCol(col2);
            }
        }
    }

    public Candy getCandy(int row, int col) {
        if (isInside(row, col)) {
            return grid[row][col];
        }
        return null;
    }

    public void setCandy(int row, int col, Candy candy) {
        if (isInside(row, col)) {
            grid[row][col] = candy;
            if (candy != null) {
                candy.setRow(row);
                candy.setCol(col);
            }
        }
    }

    public boolean isInside(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
