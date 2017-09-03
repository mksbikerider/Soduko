package name.msutherland;

import java.util.function.Function;

class CreateMatrix implements Function<Integer, Integer[][]> {
    private final int i;
    private final int j;
    private final Integer[][] cells;

    CreateMatrix(int i, int j, Integer[][] cells) {
        this.i = i;
        this.j = j;
        this.cells = cells;
    }

    @Override
    public Integer[][] apply(Integer val) {
        Integer[][] cellsCopy = Sudoku.deepCopy(cells);
        cellsCopy[i][j] = val;
        return cellsCopy;
    }
}