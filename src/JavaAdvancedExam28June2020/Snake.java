package JavaAdvancedExam28June2020;

import java.util.Scanner;

public class Snake {
    public static int snakeRow;
    public static int snakeCol;
    public static int firstBurrowRow = -1;
    public static int firstBurrowCol = -1;
    public static int secondBurrowRow = -1;
    public static int secondBurrowCol = -1;
    public static int food;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        char [][] matrix = new char[size][size];
        for (int row = 0; row < matrix.length; row++) {
            String line = scanner.nextLine();
            line = line.replace(" ", "");
            if (line.contains("S")) {
                snakeRow = row;
                snakeCol = line.indexOf("S");
            }
            if (line.contains("B")) {
                if (firstBurrowRow == -1) {
                    firstBurrowRow = row;
                    firstBurrowCol = line.indexOf("B");
                } else {
                    secondBurrowRow = row;
                    secondBurrowCol = line.indexOf("B");
                }
            }
            matrix[row] = line.toCharArray();
        }

        while (food < 10) {
            String command = scanner.nextLine();

            if ("up".equals(command)) {
                if (outOfBounds(snakeRow - 1, snakeCol, matrix)) {
                    break;
                }
                if (moveSnake(snakeRow, snakeCol, snakeRow - 1, snakeCol, matrix)) {
                    snakeRow--;
                }
            } else if ("down".equals(command)) {
                if (outOfBounds(snakeRow + 1, snakeCol, matrix)) {
                    break;
                }
                if (moveSnake(snakeRow, snakeCol, snakeRow + 1, snakeCol, matrix)) {
                    snakeRow++;
                }
            } else if ("left".equals(command)) {
                if (outOfBounds(snakeRow, snakeCol - 1, matrix)) {
                    break;
                }
                if (moveSnake(snakeRow, snakeCol, snakeRow, snakeCol - 1, matrix)) {
                    snakeCol--;
                }
            } else if ("right".equals(command)) {
                if (outOfBounds(snakeRow, snakeCol + 1, matrix)) {
                    break;
                }
                if (moveSnake(snakeRow, snakeCol, snakeRow, snakeCol + 1, matrix)) {
                    snakeCol++;
                }
            }
        }
        if (food >= 10) {
            System.out.println("You won! You fed the snake.");
        } else {
            matrix[snakeRow][snakeCol] = '.';
            System.out.println("Game over!");
        }
        System.out.printf("Food eaten: %d%n", food);
        printMatrix(matrix);
    }

    private static boolean outOfBounds(int row, int col, char[][] matrix) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix.length;
    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
    private static boolean moveSnake(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] == '*') {
            food++;
            matrix[newRow][newCol] = 'S';
        } else if(matrix[newRow][newCol] == '-') {
            matrix[newRow][newCol] = 'S';
        } else if(matrix[newRow][newCol] == 'B') {
            if (newRow == firstBurrowRow && newCol == firstBurrowCol) {
                matrix[secondBurrowRow][secondBurrowCol] = 'S';
                snakeRow = secondBurrowRow;
                snakeCol = secondBurrowCol;
            } else {
                matrix[firstBurrowRow][firstBurrowCol] = 'S';
                snakeRow = firstBurrowRow;
                snakeCol = firstBurrowCol;
            }
            matrix[oldRow][oldCol] = '.';
            matrix[newRow][newCol] = '.';
            return false;
        }
        matrix[oldRow][oldCol] = '.';
        return true;
    }
}
