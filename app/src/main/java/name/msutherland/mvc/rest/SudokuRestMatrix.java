package name.msutherland.mvc.rest;

public class SudokuRestMatrix {


    private SudokuRestRow[] rows = {new SudokuRestRow(),new SudokuRestRow(),new SudokuRestRow(),new SudokuRestRow(),new SudokuRestRow(),
            new SudokuRestRow(),new SudokuRestRow(),new SudokuRestRow(),new SudokuRestRow()};

    public SudokuRestRow[] getRows() {
        return rows;
    }

    public void setRows(SudokuRestRow[] rows) {
        this.rows = rows;
    }
}
