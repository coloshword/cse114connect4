// the Chip class, the little circles in a connect 4 game:
public class Chip {
    private String color;
    private String display;
    // constructor
    public Chip(String a) {
        color = a;
        display = ("" + color.charAt(0)).toUpperCase();
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