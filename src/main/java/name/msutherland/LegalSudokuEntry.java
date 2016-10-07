package name.msutherland;

import java.util.function.Predicate;

public class LegalSudokuEntry implements Predicate<Integer> {
    private final int rowNumber;
    private final int columnNumber;
    private final Integer[][] matrix;

    public LegalSudokuEntry(int rowNumber, int columnNumber, Integer[][] matrix) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.matrix = matrix;
    }

    @Override
    public boolean test(Integer val) {
        for (int k = 0; k < 9; ++k)  // row
            if (val == matrix[k][columnNumber])
                return false;

        for (int k = 0; k < 9; ++k) // col
            if (val == matrix[rowNumber][k])
                return false;

        int boxRowOffset = (rowNumber / 3)*3;
        int boxColOffset = (columnNumber / 3)*3;
        for (int k = 0; k < 3; ++k) // box
            for (int m = 0; m < 3; ++m)
                if (val == matrix[boxRowOffset+k][boxColOffset+m])
                    return false;

        return true;
    }
}
