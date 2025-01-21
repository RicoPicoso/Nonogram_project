import java.util.Scanner;

public class NonogramGame {
    public static void main(String[] args) {
      //  Nonogram nonogram = new Nonogram();
    }

class Nonogram {
    private static final int GRID_SIZE = 5;
    private static final int[][] SOLUTION = {
            {1, 0, 1, 1, 0},
            {1, 1, 0, 1, 1},
            {0, 1, 1, 0, 0},
            {1, 0, 1, 1, 1},
            {0, 1, 0, 0, 1}
    };

    private final char[][] grid;
    private final String[] rowHints = {"2", "3", "2", "4", "2"};
    private final String[] colHints = {"3", "3", "2", "3", "3"};

    public Nonogram() {
        this.grid = new char[GRID_SIZE][GRID_SIZE];
        // Create an empty Nonogram for solving.
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = ' ';
            }
        }
    }


}}
