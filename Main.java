import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // grid to store info
        Chip[][] grid = new Chip[6][7];
        // fill grid with empty Chip objects
        for(int row = 0; row < grid.length; row++) {
            for(int column = 0; column < grid[row].length; column++) {
                grid[row][column] = new Chip();
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
        while(gameContinue(grid)) {
            String value = (isRedTurn) ? "red" : "yellow";
            isRedTurn = !isRedTurn;
            System.out.print("Drop a " + value + " disk at column(0 - 6): ");
            int column = input.nextInt();
            if(column > 6 || column < 0) {
                System.out.println("Not a valid index, try again");
                isRedTurn = !isRedTurn;
                continue;
            }
            placeChip(grid, columns, column, value);
            displayGame(grid);
        }
    }
    public static void placeChip(Chip[][] array, Column[] columnArray, int columnPlaced, String color) {
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
        array[currentColumn.placeChip()][columnPlaced] = new Chip(color);
        currentColumn.stillOpen();
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
    }
    public static boolean gameContinue(Chip[][] array) {
        //check if game should continue
        // can't think of a non naive implementation for now
        // check rows
        for(int row = 0; row < array.length; row++) {
            for(int column = 0; column <= array[row].length / 2; column++) {
                Chip chipToCheck = array[row][column];
                if (chipToCheck.getSide() != '0' && chipToCheck.getSide() == array[row][column + 1].getSide() && chipToCheck.getSide() == array[row][column + 2].getSide() && chipToCheck.getSide() == array[row][column + 3].getSide()) {
                    winMessage(chipToCheck.getColor());
                    return false;
                }
            }
        }
        // Check columns
        for(int column = 0; column < array[0].length; column++) {
            for(int row = 5; row > array.length / 2 - 1; row--) {
                Chip chipToCheck = array[row][column];
                if (chipToCheck.getSide() != '0' && chipToCheck.getSide() == array[row - 1][column].getSide() && chipToCheck.getSide() == array[row - 2][column].getSide() && chipToCheck.getSide() == array[row - 3][column].getSide()) {
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
}