package JavaAdvancedRetakeExam13April2022;

import java.util.Scanner;

public class Armory {
    public static int myRow;
    public static int myCol;
    public static int firstMirrorRow = -1;
    public static int firstMirrorCol = -1;
    public static int secondMirrorRow = -1;
    public static int secondMirrorCol = -1;

    public static int totalSum;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[size][size];

        for (int row = 0; row < matrix.length; row++) {
            String line = scanner.nextLine();
            if (line.contains("A")) {
                myRow = row;
                myCol = line.indexOf("A");
            }

            if (line.contains("M")) {
                if (firstMirrorRow == - 1) {
                    firstMirrorRow = row;
                    firstMirrorCol = line.indexOf("M");
                } else {
                    secondMirrorRow = row;
                    secondMirrorCol = line.indexOf("M");
                }
            }
            matrix[row] = line.toCharArray();
        }

        while(totalSum < 65) {
            int oldRow = myRow;
            int oldCol = myCol;
            String command = scanner.nextLine();
            if (command.equals("up")) {
                if(outOfBounds(myRow - 1, myCol, matrix)) {
                    matrix[myRow][myCol] = '-';
                    break;
                }
                myRow--;
                move(oldRow, oldCol, myRow, myCol, matrix);
            } else if (command.equals("down")) {
                if(outOfBounds(myRow + 1, myCol, matrix)) {
                    matrix[myRow][myCol] = '-';
                    break;
                }
                myRow++;
                move(oldRow, oldCol, myRow, myCol, matrix);
            } else if (command.equals("left")) {
                if(outOfBounds(myRow, myCol - 1, matrix)) {
                    matrix[myRow][myCol] = '-';
                    break;
                }
                myCol--;
                move(oldRow, oldCol, myRow, myCol, matrix);
            } else if (command.equals("right")) {
                if(outOfBounds(myRow, myCol + 1, matrix)) {
                    matrix[myRow][myCol] = '-';
                    break;
                }
                myCol++;
                move(oldRow, oldCol, myRow, myCol, matrix);
            }
        }

        if (totalSum < 65) {
            System.out.println("I do not need more swords!");
        } else {
            System.out.println("Very nice swords, I will come back for more!");
        }
        System.out.printf("The king paid %d gold coins.%n", totalSum);
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

    private static void move(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (Character.isDigit(matrix[newRow][newCol])) {
            totalSum += Character.getNumericValue(matrix[newRow][newCol]);
            matrix[oldRow][oldCol] = '-';
            matrix[newRow][newCol] = 'A';
         } else if (matrix[newRow][newCol] == 'M') {
            if (firstMirrorRow == newRow && firstMirrorCol == newCol) {
                myRow = secondMirrorRow;
                myCol = secondMirrorCol;
                matrix[secondMirrorRow][secondMirrorCol] = 'A';
            } else {
                myRow = firstMirrorRow;
                myCol = firstMirrorCol;
                matrix[firstMirrorRow][firstMirrorCol] = 'A';
            }
            matrix[oldRow][oldCol] = '-';
            matrix[newRow][newCol] = '-';
        } else {
            matrix[oldRow][oldCol] = '-';
            matrix[newRow][newCol] = 'A';
        }
    }

    private static boolean outOfBounds(int row, int col, char[][] matrix) {
        return row < 0 || row > matrix.length - 1 || col < 0 || col > matrix.length - 1;
    }
}
