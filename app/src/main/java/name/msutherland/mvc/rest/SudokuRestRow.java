package name.msutherland.mvc.rest;

/**
 * Created by michaelsutherland on 24/10/2016.
 */
public class SudokuRestRow {

    private String[] cells = new String[9];

    public String[] getCells() {
        return cells;
    }

    public void setCells(String[] cells) {
        this.cells = cells;
    }
}
