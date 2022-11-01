import java.util.Scanner;

public class RallyRacing {

    public static int myRow = 0;
    public static int myCol = 0;
    public static int firstTunnelRow = -1;
    public static int firstTunnelCol = -1;
    public static int secondTunnelRow = -1;
    public static int secondTunnelCol = -1;
    public static int endRow;
    public static int endCol;
    public static int passedKm = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        String numberOfCar = scanner.nextLine();

        char [][] matrix = new char[size][size];

        for (int row = 0; row < matrix.length; row++) {
            String line = scanner.nextLine();
            line = line.replace(" ", "");

            if (line.contains("T")) {
                if (firstTunnelRow == -1) {
                    firstTunnelRow = row;
                    firstTunnelCol = line.indexOf("T");
                } else {
                    secondTunnelRow = row;
                    secondTunnelCol = line.indexOf("T");
                }
            }

            if (line.contains("F")) {
                endRow = row;
                endCol = line.indexOf("F");
            }

            matrix[row] = line.toCharArray();
        }

        String command = scanner.nextLine();
        boolean finish = false;
        while(!command.equals("End")) {

            int oldRow = myRow;
            int oldCol = myCol;

            if (command.equals("up")) {
                myRow--;
                move(oldRow, oldCol, myRow, myCol, matrix);
            } else if (command.equals("down")) {
                myRow++;
                move(oldRow, oldCol, myRow, myCol, matrix);
            } else if (command.equals("left")) {
                myCol--;
                move(oldRow, oldCol, myRow, myCol, matrix);
            } else if (command.equals("right")) {
                myCol++;
                move(oldRow, oldCol, myRow, myCol, matrix);
            }

            finish = myRow == endRow && myCol == endCol;
            if (finish) {
                break;
            }
            command = scanner.nextLine();
        }

        if (finish) {
            System.out.printf("Racing car %s finished the stage!%n", numberOfCar);
        } else {
            System.out.printf("Racing car %s DNF.%n", numberOfCar);
        }

        System.out.printf("Distance covered %d km.%n", passedKm);

        matrix[myRow][myCol] = 'C';
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
       if (matrix[newRow][newCol] == 'T') {
            if (firstTunnelRow == newRow && firstTunnelCol == newCol) {
                myRow = secondTunnelRow;
                myCol = secondTunnelCol;
            } else {
                myRow = firstTunnelRow;
                myCol = firstTunnelCol;
            }
            passedKm += 30;
            matrix[firstTunnelRow][firstTunnelCol] = '.';
            matrix[secondTunnelRow][secondTunnelCol] = '.';
        } else {
            passedKm += 10;
        }
    }
}
