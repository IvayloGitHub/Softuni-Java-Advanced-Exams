package JavaAdvancedExam23October2021;

import java.util.Scanner;

public class MouseAndCheese {
    public static int mouseRow;
    public static int mouseCol;
    public static int cheese;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[size][size];

        for (int row = 0; row < matrix.length; row++) {
            String line = scanner.nextLine();
            if (line.contains("M")) {
                mouseRow = row;
                mouseCol = line.indexOf("M");
            }
            matrix[row] = line.toCharArray();
        }

        String command = scanner.nextLine();

        while(!command.equals("end")) {
            int oldRow = mouseRow;
            int oldCol = mouseCol;
            if (command.equals("up")) {
                if (outOfbounds(mouseRow - 1, mouseCol, matrix)) {
                    matrix[mouseRow][mouseCol] = '-';
                    break;
                } else {
                    mouseRow--;
                }
                moveUp(oldRow, oldCol, mouseRow, mouseCol, matrix);
            } else if (command.equals("down")) {
                if (outOfbounds(mouseRow + 1, mouseCol, matrix)) {
                    matrix[mouseRow][mouseCol] = '-';
                    break;
                } else {
                    mouseRow++;
                }
                moveDown(oldRow, oldCol, mouseRow, mouseCol, matrix);
            } else if (command.equals("left")) {
                if (outOfbounds(mouseRow, mouseCol - 1, matrix)) {
                    matrix[mouseRow][mouseCol] = '-';
                    break;
                } else {
                    mouseCol--;
                }
                moveLeft(oldRow, oldCol, mouseRow, mouseCol, matrix);
            } else if (command.equals("right")) {
                if (outOfbounds(mouseRow, mouseCol + 1, matrix)) {
                    matrix[mouseRow][mouseCol] = '-';
                    break;
                } else {
                    mouseCol++;
                }
                moveRight(oldRow, oldCol, mouseRow, mouseCol, matrix);
            }
            command = scanner.nextLine();
        }
        if (!command.equals("end")) {
            System.out.println("Where is the mouse?");
        }
        if (cheese < 5) {
            System.out.printf("The mouse couldn't eat the cheeses, she needed %d cheeses more.%n", 5 - cheese);
        } else {
            System.out.printf("Great job, the mouse is fed %d cheeses!%n", cheese);
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
            matrix[newRow][newCol] = '-';
            newCol = newCol + 1;
            if (matrix[newRow][newCol] == 'c') {
                cheese++;
            }
            mouseCol = newCol;
            matrix[oldRow][oldCol] = '-';
            matrix[newRow][newCol] = 'M';
        } else if (matrix[newRow][newCol] == 'c') {
            cheese++;
            matrix[oldRow][oldCol] = '-';
            matrix[newRow][newCol] = 'M';
        } else {
            matrix[oldRow][oldCol] = '-';
            matrix[newRow][newCol] = 'M';
        }
    }

    private static void moveLeft(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] == 'B') {
            matrix[newRow][newCol] = '-';
            newCol = newCol - 1;
            if (matrix[newRow][newCol] == 'c') {
                cheese++;
            }
            mouseCol = newCol;
            matrix[oldRow][oldCol] = '-';
            matrix[newRow][newCol] = 'M';
        } else if (matrix[newRow][newCol] == 'c') {
            cheese++;
            matrix[oldRow][oldCol] = '-';
            matrix[newRow][newCol] = 'M';
        } else {
            matrix[oldRow][oldCol] = '-';
            matrix[newRow][newCol] = 'M';
        }
    }

    private static void moveDown(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] == 'B') {
            matrix[newRow][newCol] = '-';
            newRow = newRow + 1;
            if (matrix[newRow][newCol] == 'c') {
                cheese++;
            }
            mouseRow = newRow;
            matrix[oldRow][oldCol] = '-';
            matrix[newRow][newCol] = 'M';
        } else if (matrix[newRow][newCol] == 'c') {
            cheese++;
            matrix[oldRow][oldCol] = '-';
            matrix[newRow][newCol] = 'M';
        } else {
            matrix[oldRow][oldCol] = '-';
            matrix[newRow][newCol] = 'M';
        }
    }

    private static void moveUp(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] == 'B') {
            matrix[newRow][newCol] = '-';
            newRow = newRow - 1;
            if (matrix[newRow][newCol] == 'c') {
                cheese++;
            }
            mouseRow = newRow;
            matrix[oldRow][oldCol] = '-';
            matrix[newRow][newCol] = 'M';
        } else if (matrix[newRow][newCol] == 'c') {
            cheese++;
            matrix[oldRow][oldCol] = '-';
            matrix[newRow][newCol] = 'M';
        } else {
            matrix[oldRow][oldCol] = '-';
            matrix[newRow][newCol] = 'M';
        }
    }

    private static boolean outOfbounds(int row, int col, char[][] matrix) {
        return row < 0 || row > matrix.length - 1 || col < 0 || col > matrix.length - 1;
    }
}
