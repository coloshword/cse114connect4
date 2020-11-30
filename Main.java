import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // grid to store info
        Chip[][] grid = new Chip[6][7];
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
            placeChip(grid, columns, column, value);
            displayGame(grid);
        }
    }
    public static void placeChip(Chip[][] array, Column[] columnArray, int columnPlaced, String color) {
        // function puts chip in the right spot
        Column currentColumn = columnArray[columnPlaced];
        if(currentColumn.isOpen()) {
            array[currentColumn.placeChip()][columnPlaced] = new Chip(color);
            currentColumn.stillOpen();
        }
        else {
            Scanner input = new Scanner(System.in);
            while(!currentColumn.isOpen()) {
                System.out.println("Column is full, choose another column");
                System.out.print("Drop a " + color + " disk at column(0 - 6): ");
                currentColumn = columnArray[input.nextInt()];
            }
        }
    }
    public static void displayGame(Chip[][] array) {
        for(int row = 0; row < array.length; row++) {
            for(int column = 0; column < array[row].length; column++) {
                String toPrint = " ";
                if(array[row][column] == null) {
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
    }
    public static boolean gameContinue(Chip[][] array) {
        return true;
    }
}