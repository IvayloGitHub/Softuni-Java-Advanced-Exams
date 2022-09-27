import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreasureHunt {

    public static int myRow;
    public static int myCol;
    public static int endRow;
    public static int endCol;
    public static List<String> directions = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String [] array = scanner.nextLine().split(" ");
        int N = Integer.parseInt(array[0]);
        int M = Integer.parseInt(array[1]);

        char [][] matrix = new char[N][M];

        for(int row = 0; row < matrix.length; row++) {
            String line = scanner.nextLine();
            line = line.replace(" ", "");
            if (line.contains("Y")) {
                myRow = row;
                myCol = line.indexOf("Y");
            }

            if (line.contains("X")) {
                endRow = row;
                endCol = line.indexOf("X");
            }
            matrix[row] = line.toCharArray();
        }

        String command = scanner.nextLine();
        while(!command.equals("Finish")) {
            int oldRow = myRow;
            int oldCol = myCol;
            if (command.equals("up")) {
                if(outOfBounds(myRow - 1, myCol, matrix)) {
                    matrix[myRow][myCol] = 'Y';
                } else {
                    if(move(oldRow, oldCol, myRow - 1, myCol, matrix)) {
                        myRow--;
                        directions.add(command);
                    }
                }
            } else if (command.equals("down")) {
                if(outOfBounds(myRow + 1, myCol, matrix)) {
                    matrix[myRow][myCol] = 'Y';
                } else {
                    if(move(oldRow, oldCol, myRow + 1, myCol, matrix)) {
                        myRow++;
                        directions.add(command);
                    }
                }
            } else if (command.equals("left")) {
                if(outOfBounds(myRow, myCol - 1, matrix)) {
                    matrix[myRow][myCol] = 'Y';
                } else {
                    if(move(oldRow, oldCol, myRow, myCol - 1, matrix)) {
                        myCol--;
                        directions.add(command);
                    }
                }
            } else if (command.equals("right")) {
                if(outOfBounds(myRow, myCol + 1, matrix)) {
                    matrix[myRow][myCol] = 'Y';
                } else {
                    if(move(oldRow, oldCol, myRow, myCol + 1, matrix)) {
                        myCol++;
                        directions.add(command);
                    }
                }
            }
            command = scanner.nextLine();
        }

        if (myRow == endRow && myCol == endCol) {
            System.out.println("I've found the treasure!");
            System.out.println("The right path is " + String.join(", ", directions));
        } else {
            System.out.println("The map is fake!");
        }
    }

    private static boolean move(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] == 'T') {
            matrix[oldRow][oldCol] = 'Y';
            return false;
        } else {
            matrix[newRow][newCol] = 'Y';
            matrix[oldRow][oldCol] = '-';
            return true;
        }
    }

    private static boolean outOfBounds(int row, int col, char[][] matrix) {
        return row < 0 || row > matrix.length - 1 || col < 0 || col > matrix[row].length - 1;
    }
}
