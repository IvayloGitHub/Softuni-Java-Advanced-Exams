package JavaAdvancedRetakeExam17Dec2019;

import java.util.Scanner;

public class PresentDelivery {

    public static int countOfPresents;
    public static int niceKidsWithPresents;
    public static int santaRow;
    public static int santaCol;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        countOfPresents = Integer.parseInt(scanner.nextLine());
        int size = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[size][size];

        for (int row = 0; row < matrix.length; row++) {
            String line = scanner.nextLine();
            line = line.replace(" ", "");
            if (line.contains("S")) {
                santaRow = row;
                santaCol = line.indexOf("S");
            }
            matrix[row] = line.toCharArray();
        }
        String command = scanner.nextLine();
        while(!command.equals("Christmas morning")) {
            switch (command) {
                case "up":
                    if(outOfBounds(santaRow - 1, santaCol, matrix)) {
                        break;
                    }
                    moveSanta(santaRow, santaCol, santaRow - 1, santaCol, matrix);
                    santaRow--;
                    break;
                case "down":
                    if(outOfBounds(santaRow + 1, santaCol, matrix)) {
                        break;
                    }
                    moveSanta(santaRow, santaCol, santaRow + 1, santaCol, matrix);
                    santaRow++;
                    break;
                case "left":
                    if(outOfBounds(santaRow, santaCol - 1, matrix)) {
                        break;
                    }
                    moveSanta(santaRow, santaCol, santaRow, santaCol - 1, matrix);
                    santaCol--;
                    break;
                case "right":
                    if(outOfBounds(santaRow, santaCol + 1, matrix)) {
                        break;
                    }
                    moveSanta(santaRow, santaCol, santaRow, santaCol + 1, matrix);
                    santaCol++;
                    break;
            }
            if (countOfPresents < 1) {
                break;
            }
            command = scanner.nextLine();
        }
        int niceKidsLeft = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if(matrix[row][col] == 'V') {
                    niceKidsLeft++;
                }
            }
        }
        if (countOfPresents < 1) {
            System.out.println("Santa ran out of presents!");
        }
        printMatrix(matrix);
        if (niceKidsLeft > 0) {
            System.out.printf("No presents for %d nice kid/s.%n", niceKidsLeft);
        } else {
            System.out.printf("Good job, Santa! %d happy nice kid/s.%n", niceKidsWithPresents);
        }
    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static void moveSanta(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] == '-') {
            matrix[newRow][newCol] = 'S';
        } else if (matrix[newRow][newCol] == 'V') {
            matrix[newRow][newCol] = 'S';
            countOfPresents--;
            niceKidsWithPresents++;
        } else if (matrix[newRow][newCol] == 'X') {
            matrix[newRow][newCol] = 'S';
        } else if (matrix[newRow][newCol] == 'C') {
            matrix[newRow][newCol] = 'S';
            if (matrix[newRow][newCol - 1] == 'V' || matrix[newRow][newCol - 1] == 'X') {
                niceKidsWithPresents++;
                countOfPresents--;
                matrix[newRow][newCol - 1] = '-';
                if (countOfPresents < 1) {
                    matrix[oldRow][oldCol] = '-';
                    return;
                }
            }
            if (matrix[newRow][newCol + 1] == 'V' || matrix[newRow][newCol + 1] == 'X') {
                niceKidsWithPresents++;
                countOfPresents--;
                matrix[newRow][newCol + 1] = '-';
                if (countOfPresents < 1) {
                    matrix[oldRow][oldCol] = '-';
                    return;
                }
            }
            if (matrix[newRow - 1][newCol] == 'V' || matrix[newRow - 1][newCol] == 'X') {
                niceKidsWithPresents++;
                countOfPresents--;
                matrix[newRow - 1][newCol] = '-';
                if (countOfPresents < 1) {
                    matrix[oldRow][oldCol] = '-';
                    return;
                }
            }
            if (matrix[newRow + 1][newCol] == 'V' || matrix[newRow + 1][newCol] == 'X') {
                niceKidsWithPresents++;
                countOfPresents--;
                matrix[newRow + 1][newCol] = '-';
                if (countOfPresents < 1) {
                    matrix[oldRow][oldCol] = '-';
                    return;
                }
            }
        }
        matrix[oldRow][oldCol] = '-';

    }

    private static boolean outOfBounds(int row, int col, char[][] matrix) {
      return row < 0 || row >= matrix.length || col < 0 || col >= matrix.length;

    }
}
