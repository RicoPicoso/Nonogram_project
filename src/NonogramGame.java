import java.util.ArrayList;
import java.util.Scanner;

public class NonogramGame {
    public static void main(String[] args) {
        Nonogram nonogram = new Nonogram();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("=== Nonogram  ===");
            System.out.println("1. Start Game");
            System.out.println("2. Show Solution");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    nonogram.startGame();
                    break;
                case 2:
                    nonogram.showSolution();
                    break;
                case 3:
                    System.out.println("Exiting the game.");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
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
    private final String[] rowHints = {"1 2", "2 2", "2", "1 3", "1 1"};
    private final String[] colHints = {"2 1", "3", "2", "2 1", "2"};

    public Nonogram() {
        this.grid = new char[GRID_SIZE][GRID_SIZE];
        // Create an empty Nonogram for solving.
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    public void showSolution() {
        System.out.println("\n=== Solution ===");
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print((SOLUTION[i][j] == 1 ? '■' : ' ') + " ");
            }
            System.out.println();
        }
    }

    private void printGrid() {
        System.out.println("\n=== Current Grid ===");
        // Print column hints
        System.out.print("    ");
        for (String hint : colHints) {
            System.out.print(hint + "  ");
        }
        System.out.println();

        // Print the grid with row hints
        for (int i = 0; i < GRID_SIZE; i++) {
            System.out.print(rowHints[i] + " | ");
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean checkRows() {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (!checkLine(grid[i], rowHints[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumns() {
        for (int j = 0; j < GRID_SIZE; j++) {
            char[] column = new char[GRID_SIZE];
            for (int i = 0; i < GRID_SIZE; i++) {
                column[i] = grid[i][j];
            }
            if (!checkLine(column, colHints[j])) {
                return false;
            }
        }
        return true;
    }

    //*******************WIP, NOT MY SOLUTION****************************
    private boolean checkLine(char[] line, String hint) {
        // Convert hint into an array of block sizes
        String[] hintParts = hint.split("\\s+");
        int[] hintNumbers = new int[hintParts.length];
        for (int i = 0; i < hintParts.length; i++) {
            hintNumbers[i] = Integer.parseInt(hintParts[i]);
        }

        // Create an array of blocks from the current line (either row or column)
        int count = 0;
        ArrayList<Integer> blocks = new ArrayList<>();
        for (int i = 0; i < GRID_SIZE; i++) {
            if (line[i] == '■') {
                count++;
            } else if (count > 0) {
                blocks.add(count);
                count = 0;
            }
        }
        if (count > 0) {
            blocks.add(count);
        }

        // Check if the generated blocks match the hint
        if (blocks.size() != hintNumbers.length) {
            return false;
        }

        for (int i = 0; i < hintNumbers.length; i++) {
            if (blocks.get(i) != hintNumbers[i]) {
                return false;
            }
        }

        return true;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        boolean gameFinished = false;

        while (!gameFinished) {
            printGrid();
            System.out.print("\nEnter row (1-" + GRID_SIZE + "): ");
            int row = scanner.nextInt() - 1; // Convert index to 1 for easier solving.

            if (row < 0 || row >= GRID_SIZE) {
                System.out.println("Invalid row.");
                continue;
            }

            // Clear buffer
            scanner.nextLine();

            System.out.print("Enter column numbers to mark separated by ',': ");
            String input = scanner.nextLine();
            String[] columns = input.split(",");
            System.out.print("Mark as '1' or 'X': ");
            char mark = scanner.next().charAt(0);

            if (mark != '1' && mark != 'X') {
                System.out.println("Invalid mark.");
                continue;
            }

            boolean validInput = true;
            for (String col : columns) {
                try {
                    int colIndex = Integer.parseInt(col.trim()) - 1;

                    // Check if the column index is within the valid range
                    if (colIndex < 0 || colIndex >= GRID_SIZE) {
                        System.out.println("Invalid column: " + (colIndex + 1));
                        validInput = false;
                    } else {
                        grid[row][colIndex] = (mark == '1') ? '■' : 'X';
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input: " + col);
                    validInput = false;
                }
            }

            if (!validInput) {
                System.out.println("Some inputs were invalid.");
                continue;
            }

            if (checkRows() && checkColumns()) {
                printGrid();
                System.out.println("Congratulations, you won!");
                gameFinished = true;
            }
        }
    }
}
