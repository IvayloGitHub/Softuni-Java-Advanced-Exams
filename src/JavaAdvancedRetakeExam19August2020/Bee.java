package JavaAdvancedRetakeExam19August2020;

import java.util.Scanner;

public class Bee {

    public static int beeRow;
    public static int beeCol;
    public static int countOfFlowers;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[size][size];

        for (int row = 0; row < matrix.length; row++) {
            String line = scanner.nextLine();
            if (line.contains("B")) {
                beeRow = row;
                beeCol = line.indexOf("B");
            }
            matrix[row] = line.toCharArray();
        }
        String command = scanner.nextLine();
        boolean outOfBorders = false;
        while(!command.equals("End")) {
            if (command.equals("up")) {
                if(outOfBounds(beeRow - 1, beeCol, matrix)){
                    matrix[beeRow][beeCol] = '.';
                    outOfBorders = true;
                break;
                }
                moveBeeUp(beeRow, beeCol, beeRow - 1, beeCol, matrix);
                beeRow--;
            } else if (command.equals("down")) {
                if(outOfBounds(beeRow + 1, beeCol, matrix)){
                    matrix[beeRow][beeCol] = '.';
                    outOfBorders = true;
                    break;
                }
                moveBeeDown(beeRow, beeCol, beeRow + 1, beeCol, matrix);
                beeRow++;
            } else if (command.equals("left")) {
                if(outOfBounds(beeRow , beeCol - 1, matrix)){
                    matrix[beeRow][beeCol] = '.';
                    outOfBorders = true;
                    break;
                }
                moveBeeLeft(beeRow, beeCol, beeRow, beeCol - 1, matrix);
                beeCol--;
            } else if (command.equals("right")) {
                if(outOfBounds(beeRow, beeCol + 1, matrix)){
                    matrix[beeRow][beeCol] = '.';
                    outOfBorders = true;
                    break;
                }
                moveBeeRight(beeRow, beeCol, beeRow, beeCol + 1, matrix);
                beeCol++;
            } else if (countOfFlowers >= 5) {
                break;
            }
            command = scanner.nextLine();
        }
        if(outOfBorders) {
            System.out.println("The bee got lost!");
        }

        if (countOfFlowers >= 5) {
            System.out.printf("Great job, the bee manage to pollinate %d flowers!%n", countOfFlowers);
        } else {
            System.out.printf("The bee couldn't pollinate the flowers, she needed %d flowers more%n", 5 - countOfFlowers);
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

    private static void moveBeeRight(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] == '.') {
            matrix[newRow][newCol] = 'B';
        } else if (matrix[newRow][newCol] == 'f') {
            matrix[newRow][newCol] = 'B';
            countOfFlowers++;
        } else if (matrix[newRow][newCol] == 'O') {
            matrix[newRow][newCol] = '.';
            if (matrix[newRow][newCol + 1] == 'f') {
                countOfFlowers++;
            }
            matrix[newRow][newCol + 1] = 'B';
            beeCol++;
        }
        matrix[oldRow][oldCol] = '.';
    }

    private static void moveBeeLeft(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] == '.') {
            matrix[newRow][newCol] = 'B';
        } else if (matrix[newRow][newCol] == 'f') {
            matrix[newRow][newCol] = 'B';
            countOfFlowers++;
        } else if (matrix[newRow][newCol] == 'O') {
            matrix[newRow][newCol] = '.';
            if (matrix[newRow][newCol - 1] == 'f') {
                countOfFlowers++;
            }
            matrix[newRow][newCol - 1] = 'B';
            beeCol--;
        }
        matrix[oldRow][oldCol] = '.';
    }

    private static void moveBeeDown(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] == '.') {
            matrix[newRow][newCol] = 'B';
        } else if (matrix[newRow][newCol] == 'f') {
            matrix[newRow][newCol] = 'B';
            countOfFlowers++;
        } else if (matrix[newRow][newCol] == 'O') {
            matrix[newRow][newCol] = '.';
            if (matrix[newRow + 1][newCol] == 'f') {
                countOfFlowers++;
            }
            matrix[newRow + 1][newCol] = 'B';
            beeRow++;
        }
        matrix[oldRow][oldCol] = '.';
    }

    private static void moveBeeUp(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] == '.') {
            matrix[newRow][newCol] = 'B';
        } else if (matrix[newRow][newCol] == 'f') {
            matrix[newRow][newCol] = 'B';
            countOfFlowers++;
        } else if (matrix[newRow][newCol] == 'O') {
            matrix[newRow][newCol] = '.';
            if (matrix[newRow - 1][newCol] == 'f') {
                countOfFlowers++;
            }
            matrix[newRow - 1][newCol] = 'B';
            beeRow--;
        }
        matrix[oldRow][oldCol] = '.';
    }

    private static boolean outOfBounds(int row, int col, char[][] matrix) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix.length;
    }
}
