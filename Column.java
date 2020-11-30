// the column class to deal with column exclusive issues
public class Column {
    private int openIndex;
    private boolean isOpen;
    // constructor
    public Column() {
        openIndex = 5;
        isOpen = true;
    }
    // functions
    public boolean isOpen() {
        return isOpen;
    }
    public int placeChip() {
        return openIndex--;
    }
    public void stillOpen() {
        if(openIndex < 0) {
            isOpen = false;
        }
    }
}