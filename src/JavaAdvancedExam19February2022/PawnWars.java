package JavaAdvancedExam19February2022;

import java.util.Scanner;

public class PawnWars {
    public static int blackRow;
    public static int blackCol;
    public static int whiteRow;
    public static int whiteCol;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = 8;
        char[][] matrix = new char[size][size];

        for (int row = 0; row < matrix.length; row++) {
            String line = scanner.nextLine();
            if(line.contains("b")) {
                blackRow = row;
                blackCol = line.indexOf("b");
            }

            if (line.contains("w")) {
                whiteRow = row;
                whiteCol = line.indexOf("w");
            }
        }
        boolean war = false;

        while(whiteRow != 0 && blackRow != matrix.length - 1 && !war) {
            if (whitePawnWin()) {
                String colAndRow = findColAndRow(blackRow, blackCol);
                System.out.printf("Game over! White capture on %s.%n", colAndRow);
                war = true;
            }
            whiteRow--;

            if(blackPawnWin()) {
                String colAndRow = findColAndRow(whiteRow, whiteCol);
                System.out.printf("Game over! Black capture on %s.%n", colAndRow);
                war = true;
            }
            blackRow++;
        }
        if (!war) {
            if (whiteRow == 0) {
                System.out.printf("Game over! White pawn is promoted to a queen at %s.%n", findColAndRow(whiteRow, whiteCol));
            } else {
                System.out.printf("Game over! Black pawn is promoted to a queen at %s.%n", findColAndRow(blackRow, blackCol));
            }
        }
    }

    private static boolean blackPawnWin() {
        if (blackRow + 1== whiteRow && (blackCol + 1 == whiteCol || blackCol - 1 == whiteCol)) {
            return true;
        } else {
            return false;
        }
    }

    private static String findColAndRow(int rowPawn, int colPawn) {
        char[] col = new char[]{'a', 'b','c','d','e','f','g', 'h'};
        char[] row = new char[]{'8', '7','6','5','4','3','2', '1'};
        return String.valueOf(col[colPawn]) + row[rowPawn];
    }

    private static boolean whitePawnWin() {
        if (whiteRow - 1 == blackRow && (whiteCol - 1 == blackCol || whiteCol + 1 == blackCol)) {
            return true;
        } else {
            return false;
        }
    }
}
