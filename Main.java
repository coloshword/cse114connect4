import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // grid to store info
        Chip[][] grid = new Chip[6][7];
        // fill grid with empty Chip objects
        for(int row = 0; row < grid.length; row++) {
            for(int column = 0; column < grid[row].length; column++) {
                grid[row][column] = new Chip(row, column);
            }
        }
        //Create the column objects
        Column[] columns = new Column[7];
        for(int i = 0; i < columns.length; i++) {
            columns[i] = new Column();
        }
        Scanner input = new Scanner(System.in);
        boolean isRedTurn = true;
        displayGame(grid);
        Chip mostRecent = grid[0][0];
        while(gameContinue(grid, mostRecent)) {
            String value = (isRedTurn) ? "red" : "yellow";
            isRedTurn = !isRedTurn;
            System.out.print("Drop a " + value + " disk at column(0 - 6): ");
            int column = input.nextInt();
            if(column > 6 || column < 0) {
                System.out.println("Not a valid index, try again");
                isRedTurn = !isRedTurn;
                continue;
            }
            mostRecent = placeChip(grid, columns, column, value);
            displayGame(grid);
        }
    }
    public static Chip placeChip(Chip[][] array, Column[] columnArray, int columnPlaced, String color) {
        // function puts chip in the right spot
        Column currentColumn = columnArray[columnPlaced];
        if(!currentColumn.isOpen()) {
            Scanner input = new Scanner(System.in);
            while (!currentColumn.isOpen()) {
                System.out.println("Column is full, choose another column");
                displayGame(array);
                System.out.print("Drop a " + color + " disk at column(0 - 6): ");
                columnPlaced = input.nextInt();
                if(columnPlaced > 6 || columnPlaced < 0) {
                    System.out.println("Not a valid index, try again");
                    continue;
                }
                currentColumn = columnArray[columnPlaced];
            }
        }
        int placedRow = currentColumn.placeChip();
        array[placedRow][columnPlaced] = new Chip(color, placedRow, columnPlaced);
        currentColumn.stillOpen();
        return array[placedRow][columnPlaced];
    }
    public static void displayGame(Chip[][] array) {
        for(int row = 0; row < array.length; row++) {
            for(int column = 0; column < array[row].length; column++) {
                String toPrint = " ";
                if(array[row][column].getSide() == '0') {
                    toPrint = " ";
                }
                else {
                    toPrint = array[row][column].getDisplay();
                }
                System.out.printf("|%s", toPrint);
                if (column == 6)
                    System.out.print("|");
            }
            System.out.println();
        }
        System.out.println("...............");
        System.out.println(" 0 1 2 3 4 5 6");
    }
    public static boolean gameContinue(Chip[][] array, Chip current) {
        int row = current.getLocation()[0];
        for(int column = 0; column <= array[row].length / 2; column++) {
            Chip chipToCheck = array[row][column];
            if(chipToCheck.getSide() != '0' && chipToCheck.getSide() == array[row][column + 1].getSide() && chipToCheck.getSide() == array[row][column + 2].getSide() && chipToCheck.getSide() == array[row][column + 3].getSide()) {
                winMessage(chipToCheck.getColor());
                return false;
            }
        }
        // Check columns
        int column = current.getLocation()[1];
        for(int i = 5; i > 2; i--) {
            Chip chipToCheck = array[i][column];
            if(chipToCheck.getSide() != '0' && chipToCheck.getSide() == array[i - 1][column].getSide() && chipToCheck.getSide() == array[i - 2][column].getSide() && chipToCheck.getSide() == array[i - 3][column].getSide()) {
                winMessage(chipToCheck.getColor());
                return false;
            }
        }
        // Check diagonals
        int[][] Diagonals = twoDiagonals(current.getLocation());
        // left to right diagonal first
        for(int irow = Diagonals[0][0]; irow <= 3; irow++) {
            for(int icolumn = Diagonals[0][1]; icolumn < 4; icolumn++) {
                Chip chipToCheck = array[irow][icolumn];
                if(chipToCheck.getSide() != '0' && chipToCheck.getSide() == array[irow + 1][icolumn + 1].getSide() && chipToCheck.getSide() == array[irow + 2][icolumn + 2].getSide() && chipToCheck.getSide() == array[irow + 3][icolumn + 3].getSide()) {
                    winMessage(chipToCheck.getColor());
                    return false;
                }
            }
        }
        for(int irow = Diagonals[1][0]; irow < 3; irow++) {
            for(int icolumn = Diagonals[1][1]; icolumn > 2; icolumn--) {
                Chip chipToCheck = array[irow][icolumn];
                if(chipToCheck.getSide() != '0' && chipToCheck.getSide() == array[irow + 1][icolumn - 1].getSide() && chipToCheck.getSide() == array[irow + 2][icolumn - 2].getSide() && chipToCheck.getSide() == array[irow + 3][icolumn - 3].getSide()) {
                    winMessage(chipToCheck.getColor());
                    return false;
                }
            }
        }
        return true;
    }
    public static void winMessage(String value) {
        System.out.printf("The %s player won.\n", value);
    }
    public static int[][] twoDiagonals(int[] loc) {
        int subtract = Math.min(loc[0], loc[1]);
        int[][] Diagonals = new int[2][2];
        Diagonals[0][0] = loc[0] - subtract;
        Diagonals[0][1] = loc[1] - subtract;
        // get the new diagonal
        int x = loc[0];
        int y = loc[1];
        subtract = Math.min(x, (6 - y));
        Diagonals[1][0] = x - subtract;
        Diagonals[1][1] = y + subtract;
        return Diagonals;
    }
}