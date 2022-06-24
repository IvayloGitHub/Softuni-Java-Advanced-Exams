package JavaAdvancedRetakeExam18August2021;

import java.util.Scanner;

public class FormulaOne {
    public static int myRow;
    public static int myCol;
    public static int finishRow;
    public static int finishCol;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        int numberOfCommands = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[size][size];

        for (int row = 0; row < matrix.length; row++) {
            String line = scanner.nextLine();
            if (line.contains("P")) {
                myRow = row;
                myCol = line.indexOf("P");
            }
            if(line.contains("F")) {
                finishRow = row;
                finishCol = line.indexOf("F");
            }
            matrix[row] = line.toCharArray();
        }
        boolean won = false;
        while(numberOfCommands > 0 && !won) {
            String command = scanner.nextLine();
            int oldRow = myRow;
            int oldCol = myCol;
            if (command.equals("up")) {
                if (myRow - 1 < 0) {
                    myRow = matrix.length - 1;
                } else {
                    myRow--;
                }
                moveUp(oldRow, oldCol, myRow, myCol, matrix);
            } else if (command.equals("down")) {
                if (myRow + 1 > matrix.length - 1) {
                    myRow = 0;
                } else {
                    myRow++;
                }
                moveDown(oldRow, oldCol, myRow, myCol, matrix);
            } else if (command.equals("left")) {
                if (myCol - 1 < 0) {
                    myCol = matrix.length - 1;
                } else {
                    myCol--;
                }
                moveLeft(oldRow, oldCol, myRow, myCol, matrix);
            } else if (command.equals("right")) {
                if (myCol + 1 >  matrix.length - 1) {
                    myCol = 0;
                } else {
                    myCol++;
                }
                moveRight(oldRow, oldCol, myRow, myCol, matrix);
            }

            won = myRow == finishRow && myCol == finishCol;
            numberOfCommands--;
        }

        if(won) {
            System.out.println("Well done, the player won first place!");
        } else {
            System.out.println("Oh no, the player got lost on the track!");
        }
        printMatrix(matrix);

    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }

    private static void moveRight(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] == 'B') {
            if (newCol + 1 > matrix.length - 1) {
                newCol = 0;
            } else{
                newCol = newCol + 1;
            }
            matrix[newRow][newCol] = 'P';
            matrix[oldRow][oldCol] = '.';
            myCol = newCol;
        } else if (matrix[newRow][newCol] == 'T') {
            myCol = oldCol;
        } else {
            matrix[newRow][newCol] = 'P';
            matrix[oldRow][oldCol] = '.';
        }
    }

    private static void moveLeft(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] == 'B') {
            if (newCol - 1 < 0) {
                newCol = matrix.length - 1;
            } else{
                newCol = newCol - 1;
            }
            matrix[newRow][newCol] = 'P';
            matrix[oldRow][oldCol] = '.';
            myCol = newCol;
        } else if (matrix[newRow][newCol] == 'T') {
            myCol = oldCol;
        } else {
            matrix[newRow][newCol] = 'P';
            matrix[oldRow][oldCol] = '.';
        }
    }

    private static void moveDown(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] == 'B') {
            if (newRow + 1 > matrix.length - 1) {
                newRow = 0;
            } else{
                newRow = newRow + 1;
            }
            matrix[newRow][newCol] = 'P';
            matrix[oldRow][oldCol] = '.';
            myRow = newRow;
        } else if (matrix[newRow][newCol] == 'T') {
            myRow = oldRow;
        } else {
            matrix[newRow][newCol] = 'P';
            matrix[oldRow][oldCol] = '.';
        }
    }

    private static void moveUp(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] == 'B') {
            if (newRow - 1 < 0) {
                newRow = matrix.length - 1;
            } else{
                newRow = newRow - 1;
            }
            matrix[newRow][newCol] = 'P';
            matrix[oldRow][oldCol] = '.';
            myRow = newRow;
        } else if (matrix[newRow][newCol] == 'T') {
            myRow = oldRow;
        } else {
            matrix[newRow][newCol] = 'P';
            matrix[oldRow][oldCol] = '.';
        }
    }
}
