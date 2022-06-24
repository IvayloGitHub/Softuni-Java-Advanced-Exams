package JavaAdvancedRetakeExam16December2020;

import java.util.Scanner;

public class Selling {

    public static int myRow;
    public static int myCol;
    public static int firstPillarRow = -1;
    public static int firstPillarCol = -1;
    public static int secondPillarRow = -1;
    public static int secondPillarCol = -1;
    public static int amount;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[size][size];

        for (int row = 0; row < matrix.length; row++) {
            String line = scanner.nextLine();
            if (line.contains("S")) {
                myRow = row;
                myCol = line.indexOf("S");
            }

            if (line.contains("O")) {
                if (firstPillarRow == -1) {
                    firstPillarRow = row;
                    firstPillarCol = line.indexOf("O");
                } else {
                    secondPillarRow = row;
                    secondPillarCol = line.indexOf("O");
                }
            }
            matrix[row] = line.toCharArray();
        }

        while (amount < 50) {
            String command = scanner.nextLine();
            if (command.equals("up")) {
                if (outOfBounds(myRow - 1, myCol, matrix)) {
                    matrix[myRow][myCol] = '-';
                    break;
                }
                if (move(myRow, myCol, myRow - 1, myCol, matrix)) {
                    myRow--;
                }
            } else if (command.equals("down")) {
                if (outOfBounds(myRow + 1, myCol, matrix)) {
                    matrix[myRow][myCol] = '-';
                    break;
                }
                if (move(myRow, myCol, myRow + 1, myCol, matrix)) {
                    myRow++;
                }
            } else if (command.equals("left")) {
                if (outOfBounds(myRow, myCol - 1, matrix)) {
                    matrix[myRow][myCol] = '-';
                    break;
                }
                if (move(myRow, myCol, myRow, myCol - 1, matrix)) {
                    myCol--;
                }
            } else if (command.equals("right")) {
                if (outOfBounds(myRow, myCol + 1, matrix)) {
                    matrix[myRow][myCol] = '-';
                    break;
                }
                if (move(myRow, myCol, myRow, myCol + 1, matrix)) {
                    myCol++;
                }
            }
        }
        if (amount >= 50) {
            System.out.println("Good news! You succeeded in collecting enough money!");
        } else {
            System.out.println("Bad news, you are out of the bakery.");
        }
        System.out.printf("Money: %d%n", amount);
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

    private static boolean move(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (Character.isDigit(matrix[newRow][newCol])) {
            amount += Character.getNumericValue(matrix[newRow][newCol]);
            matrix[newRow][newCol] = 'S';
        } else if (matrix[newRow][newCol] == '-') {
            matrix[newRow][newCol] = 'S';
        } else if (matrix[newRow][newCol] == 'O') {
            matrix[newRow][newCol] = '-';
            matrix[oldRow][oldCol] = '-';
            if (newRow == firstPillarRow && newCol == firstPillarCol) {
                matrix[secondPillarRow][secondPillarCol] = 'S';
                myRow = secondPillarRow;
                myCol = secondPillarCol;
            } else {
                matrix[firstPillarRow][firstPillarCol] = 'S';
                myRow = firstPillarRow;
                myCol = firstPillarCol;
            }
            return false;
        }
        matrix[oldRow][oldCol] = '-';
        return true;
    }

    private static boolean outOfBounds(int row, int col, char[][] matrix) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix.length;
    }
}
