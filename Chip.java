// the Chip class, the little circles in a connect 4 game:
public class Chip {
    private String color;
    private String display;
    private char YorR;
    //constructor for empty chips
    public Chip() {
        YorR = '0';
    }
    // constructor for actual chip
    public Chip(String a) {
        color = a;
        YorR = color.charAt(0);
        display = ("" + YorR).toUpperCase();
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
    public String toString() {
        return "This chip has color " + getColor();
    }
}