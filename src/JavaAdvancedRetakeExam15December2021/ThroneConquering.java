package JavaAdvancedRetakeExam15December2021;

import java.util.Scanner;

public class ThroneConquering {
    public static int parisRow;
    public static int parisCol;
    public static int helenRow;
    public static int helenCol;

    public static int energyOfParis;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int energy = Integer.parseInt(scanner.nextLine());
        energyOfParis = energy;
        int size = Integer.parseInt(scanner.nextLine());

        char[][]matrix = new char[size][size];

        for (int row = 0; row < matrix.length; row++) {
            String line = scanner.nextLine();

            if (line.contains("P")) {
                parisRow = row;
                parisCol = line.indexOf("P");
            }

            if (line.contains("H")) {
                helenRow = row;
                helenCol = line.indexOf("H");
            }
            matrix[row] = line.toCharArray();
        }
        boolean reachedHelen = false;
        while (energyOfParis > 0) {
            String[] commandArray = scanner.nextLine().split(" ");
            String command = commandArray[0];
            int spawnRow = Integer.parseInt(commandArray[1]);
            int spawnCol = Integer.parseInt(commandArray[2]);
            matrix[spawnRow][spawnCol] = 'S';
            int oldRow = parisRow;
            int oldCol = parisCol;
            if (command.equals("up")) {
                if (outOfBounds(parisRow - 1, parisCol, matrix)) {
                    energyOfParis--;
                    continue;
                }
                parisRow--;
                energyOfParis--;
                move(oldRow, oldCol, parisRow, parisCol, matrix);
            } else if (command.equals("down")) {
                if (outOfBounds(parisRow + 1, parisCol, matrix)) {
                    energyOfParis--;
                    continue;
                }
                parisRow++;
                energyOfParis--;
                move(oldRow, oldCol, parisRow, parisCol, matrix);
            } else if (command.equals("left")) {
                if (outOfBounds(parisRow, parisCol - 1, matrix)) {
                    energyOfParis--;
                    continue;
                }
                parisCol--;
                energyOfParis--;
                move(oldRow, oldCol, parisRow, parisCol, matrix);
            } else if (command.equals("right")) {
                if (outOfBounds(parisRow, parisCol + 1, matrix)) {
                    energyOfParis--;
                    continue;
                }
                parisCol++;
                energyOfParis--;
                move(oldRow, oldCol, parisRow, parisCol, matrix);
            }
            reachedHelen = parisRow == helenRow && parisCol == helenCol;
            if (reachedHelen) {
                break;
            }
            if(energyOfParis <= 0) {
                matrix[parisRow][parisCol] = 'X';
            }
        }
        if(reachedHelen) {
            System.out.printf("Paris has successfully abducted Helen! Energy left: %d%n", energyOfParis);
        } else {
            System.out.printf("Paris died at %d;%d.%n", parisRow, parisCol);
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

    private static void move(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] == 'S') {
            energyOfParis -= 2;
            if (energyOfParis <= 0) {
                matrix[newRow][newCol] = 'X';
            } else {
                matrix[newRow][newCol] = 'P';
            }
            matrix[oldRow][oldCol] = '-';
        } else {
            matrix[oldRow][oldCol] = '-';
            matrix[newRow][newCol] = '-';
        }

    }

    private static boolean outOfBounds(int row, int col, char[][] matrix) {
        return row < 0 || row > matrix.length - 1 || col < 0 || col > matrix.length - 1;
    }
}
