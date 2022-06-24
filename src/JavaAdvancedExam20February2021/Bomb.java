package JavaAdvancedExam20February2021;

import java.util.Scanner;

public class Bomb {

    public static int sapperRow;
    public static int sapperCol;
    public static int countOfBombs;
    public static boolean finishLine = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        String[] commands = scanner.nextLine().split(",");

        char[][] matrix = new char[size][size];

        for (int row = 0; row < matrix.length; row++) {
            String line = scanner.nextLine();
            line = line.replace(" ", "");

            if (line.contains("s")) {
                sapperRow = row;
                sapperCol = line.indexOf("s");
            }

            char[] bombs = line.toCharArray();
            for (char bomb : bombs) {
                if (bomb == 'B') {
                    countOfBombs++;
                }
            }
            matrix[row] = line.toCharArray();
        }


        for (String command : commands) {
            if (command.equals("up")) {
                if (outOfBounds(sapperRow - 1, sapperCol, matrix)) {
                    continue;
                }
                moveSapper(sapperRow, sapperCol, sapperRow - 1, sapperCol, matrix);
                sapperRow--;


            } else if (command.equals("down")) {
                if (outOfBounds(sapperRow + 1, sapperCol, matrix)) {
                    continue;
                }
                moveSapper(sapperRow, sapperCol, sapperRow + 1, sapperCol, matrix);
                sapperRow++;


            } else if (command.equals("left")) {
                if (outOfBounds(sapperRow, sapperCol - 1, matrix)) {
                    continue;
                }
                moveSapper(sapperRow, sapperCol, sapperRow, sapperCol - 1, matrix);
                sapperCol--;


            } else if (command.equals("right")) {
                if (outOfBounds(sapperRow, sapperCol + 1, matrix)) {
                    continue;
                }
                moveSapper(sapperRow, sapperCol, sapperRow, sapperCol + 1, matrix);
                sapperCol++;


            }

            if (finishLine) {
                break;
            }

        }

        if (countOfBombs == 0) {
            System.out.println("Congratulations! You found all bombs!");
        } else if (finishLine) {
            System.out.printf("END! %d bombs left on the field", countOfBombs);
        } else {
            System.out.printf("%d bombs left on the field. Sapper position: (%d,%d)", countOfBombs, sapperRow, sapperCol);
        }

    }

    private static void moveSapper(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] == 'B') {
            countOfBombs--;
            System.out.println("You found a bomb!");
        } else if (matrix[newRow][newCol] == 'e') {
            finishLine = true;
        }
        matrix[newRow][newCol] = 's';
        matrix[oldRow][oldCol] = '+';

    }

    private static boolean outOfBounds(int row, int col, char[][] matrix) {
        return row < 0 || row > matrix.length - 1 || col < 0 || col > matrix.length - 1;
    }
}