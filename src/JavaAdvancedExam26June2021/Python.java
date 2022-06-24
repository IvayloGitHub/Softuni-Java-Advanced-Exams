package JavaAdvancedExam26June2021;

import java.util.Scanner;

public class Python {

    public static int length = 1;
    public static int pythonRow;
    public static int pythonCol;
    public static boolean enemy = false;
    public static int food;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        String[] commands = scanner.nextLine().split(", ");

        char[][] matrix = new char[size][size];

        for (int row = 0; row < matrix.length; row++) {
            String line = scanner.nextLine();
            line = line.replace(" ", "");

            if(line.contains("s")) {
                pythonRow = row;
                pythonCol = line.indexOf("s");
            }
            matrix[row] = line.toCharArray();
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if(matrix[row][col] == 'f') {
                    food++;
                }
            }
        }

        for (String command : commands) {
            int oldRow = pythonRow;
            int oldCol = pythonCol;

            if(command.equals("up")) {
                if (pythonRow - 1 < 0) {
                    pythonRow = matrix.length - 1;
                } else {
                    pythonRow--;
                }
                move(oldRow, oldCol, pythonRow, pythonCol, matrix);
            } else if (command.equals("down")) {
                if (pythonRow + 1 > matrix.length - 1) {
                    pythonRow = 0;
                } else {
                    pythonRow++;
                }
                move(oldRow, oldCol, pythonRow, pythonCol, matrix);
            } else if (command.equals("left")) {
                if (pythonCol - 1 < 0) {
                    pythonCol = matrix.length - 1;
                } else {
                    pythonCol--;
                }
                move(oldRow, oldCol, pythonRow, pythonCol, matrix);
            } else if (command.equals("right")) {
                if (pythonCol + 1 > matrix.length - 1) {
                    pythonCol = 0;
                } else {
                    pythonCol++;
                }
                move(oldRow, oldCol, pythonRow, pythonCol, matrix);
            }

            if (enemy) {
                break;
            }
        }

        if (food == 0) {
            System.out.printf("You win! Final python length is %d%n", length);
        } else if (enemy) {
            System.out.println("You lose! Killed by an enemy!");
        } else {
            System.out.printf("You lose! There is still %d food to be eaten.%n", food);
        }
    }

    private static void move(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] == 'f') {
            food--;
            length++;
        } else if (matrix[newRow][newCol] == 'e') {
            enemy = true;
        }
        matrix[oldRow][oldCol] = '*';
        matrix[newRow][newCol] = 's';
    }
}
