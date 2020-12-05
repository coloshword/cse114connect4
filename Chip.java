// the Chip class, the little circles in a connect 4 game:
public class Chip {
    private String color;
    private String display;
    private char YorR;
    private int[] location;
    //constructor for empty chips
    public Chip(int row, int column) {
        YorR = '0';
        location = new int[]{row, column};
    }
    // constructor for actual chip
    public Chip(String a, int row, int column) {
        location = new int[]{row, column};
        color = a;
        YorR = color.charAt(0);
        display = ("" + YorR).toUpperCase();
    }
    public int[] getLocation() {
        return location;
    }
    public char getSide() {
        return YorR;
    }
    public String getColor() {
        return color;
    }
    public String getDisplay() {
        return display;
    }
//    //Win condition function
//    public boolean isDiagonal() {
//        // check the left to right diagonal
//        int counter = 0;
//        char sideToCheck = getSide();
//
//    }
    public String toString() {
        return "This chip has color " + getColor();
    }
//    public static boolean checkNeighbor(int[] coordinate) {
//
//    }
}



