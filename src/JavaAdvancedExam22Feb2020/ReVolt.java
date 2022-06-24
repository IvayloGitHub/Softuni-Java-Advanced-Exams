package JavaAdvancedExam22Feb2020;

import java.util.Scanner;

public class ReVolt {

    public static int playerRow;
    public static int playerCol;
    public static int endRow;
    public static int endCol;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        int numberOfCommands = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[size][size];

        for (int row = 0; row < matrix.length; row++) {
            String line = scanner.nextLine();
            line = line.replace(" ", "");
            if (line.contains("f")) {
                playerRow = row;
                playerCol = line.indexOf("f");
            }

            if (line.contains("F")) {
                endRow = row;
                endCol = line.indexOf("F");
            }
            matrix[row] = line.toCharArray();
        }

        boolean won = false;

        while(numberOfCommands > 0 && !won) {
            String command = scanner.nextLine();
            int oldRow = playerRow;
            int oldCol = playerCol;
            switch(command) {
                case "up":
                    if (outOfBounds(playerRow - 1, playerCol, matrix)) {
                        playerRow = matrix.length - 1;
                    } else {
                        playerRow--;
                    }
                    moveUp(oldRow, oldCol, playerRow, playerCol, matrix);
                    break;
                case "down":
                    if (outOfBounds(playerRow + 1, playerCol, matrix)) {
                        playerRow = 0;
                    } else {
                        playerRow++;
                    }
                    moveDown(oldRow, oldCol, playerRow, playerCol, matrix);
                    break;
                case "right":
                    if (outOfBounds(playerRow, playerCol + 1, matrix)) {
                        playerCol = 0;
                    } else {
                        playerCol++;
                    }
                    moveRight(oldRow, oldCol, playerRow, playerCol, matrix);
                    break;
                case "left":
                    if (outOfBounds(playerRow, playerCol - 1, matrix)) {
                        playerCol = matrix.length - 1;
                    } else {
                        playerCol--;
                    }
                    moveLeft(oldRow, oldCol, playerRow, playerCol, matrix);
                    break;
                default:
                    break;
            }
            won = playerRow == endRow && playerCol == endCol;
            numberOfCommands--;
        }
        if(won) {
            System.out.println("Player won!");
        } else {
            System.out.println("Player lost!");
        }
        printMatrix(matrix);
    }


    private static boolean outOfBounds(int row, int col, char[][]matrix) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix.length;
    }


    private static void moveUp(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] != 'B' && matrix[newRow][newCol] != 'T') {
            matrix[oldRow][oldCol] = '-';
            matrix[newRow][newCol] = 'f';
        } else if (matrix[newRow][newCol] == 'B')  {
            if(newRow - 1 < 0) {
                newRow = matrix.length - 1;
                matrix[newRow][newCol] = 'f';
                playerRow = matrix.length - 1;
            } else {
                matrix[newRow-1][newCol] = 'f';
                playerRow--;
            }
            matrix[oldRow][oldCol] = '-';
        } else if (matrix[newRow][newCol] == 'T') {
        playerRow = oldRow;
        }
    }

    private static void moveDown(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] != 'B' && matrix[newRow][newCol] != 'T') {
            matrix[oldRow][oldCol] = '-';
            matrix[newRow][newCol] = 'f';
        } else if (matrix[newRow][newCol] == 'B')  {
            if(newRow + 1 > matrix.length - 1) {
                newRow = 0;
                matrix[newRow][newCol] = 'f';
                playerRow = 0;
            } else {
                matrix[newRow + 1][newCol] = 'f';
                playerRow++;
            }
            matrix[oldRow][oldCol] = '-';
        } else if (matrix[newRow][newCol] == 'T') {
         playerRow = oldRow;
        }
    }

    private static void moveRight(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] != 'B' && matrix[newRow][newCol] != 'T') {
            matrix[oldRow][oldCol] = '-';
            matrix[newRow][newCol] = 'f';
        } else if (matrix[newRow][newCol] == 'B')  {
            if(newCol + 1 > matrix.length - 1) {
                newCol = 0;
                matrix[newRow][newCol] = 'f';
                playerCol = 0;
            } else {
                matrix[newRow][newCol+1] = 'f';
                playerCol++;
            }
            matrix[oldRow][oldCol] = '-';
        } else if (matrix[newRow][newCol] == 'T') {
              playerCol=oldCol;
        }
    }

    private static void moveLeft(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] != 'B' && matrix[newRow][newCol] != 'T') {
            matrix[oldRow][oldCol] = '-';
            matrix[newRow][newCol] = 'f';
        } else if (matrix[newRow][newCol] == 'B') {
            if (newCol - 1 < 0) {
                newCol = matrix.length - 1;
                matrix[newRow][newCol] = 'f';
                playerCol = matrix.length - 1;
            } else {
                matrix[newRow][newCol-1] = 'f';
                playerCol--;
            }
            matrix[oldRow][oldCol] = '-';
        } else if (matrix[newRow][newCol] == 'T') {
            playerCol = oldCol;
        }
    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}

