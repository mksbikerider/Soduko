package name.msutherland;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class StreamSolve implements Solve, Function<int[][], List<int[][]> >{

    private static final List<Integer> VALID_VALUES = Arrays.asList(1,2,3,4,5,6,7,8,9);

    private int i;
    private int j;

    public StreamSolve(){
        this(0,0);
    }

    StreamSolve(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public List<int[][]> solve(int[][] cells){

        if (i == 9 && j == 8){
            // All squares filled, valid grid
            return Collections.singletonList(cells);
        }
        if (i == 9) { // Go to next line
            i = 0;
            ++j;
        }
        if (cells[i][j] != 0) {  // skip filled cells
            return new StreamSolve(i + 1, j).solve(cells);
        }

        LegalSudokuEntry legal = new LegalSudokuEntry(i, j, cells);
        CreateMatrix createMatrix = new CreateMatrix(i,j,cells);
        StreamSolve solve = new StreamSolve(i+1, j);

        return VALID_VALUES.parallelStream().unordered().filter(legal)
                .map(createMatrix)
                .map(solve).flatMap(List::parallelStream).collect(Collectors.toList());

    }

    @Override
    public List<int[][]> apply(int[][] ints) {
        return solve(ints);
    }

    private class CreateMatrix implements Function<Integer, int[][]>{
        private final int i;
        private final int j;
        private final int[][] cells;

        private CreateMatrix(int i, int j, int[][] cells) {
            this.i = i;
            this.j = j;
            this.cells = cells;
        }

        @Override
        public int[][] apply(Integer val) {
            int[][] cellsCopy = Sudoku.deepCopy(cells);
            cellsCopy[i][j] = val;
            return cellsCopy;
        }
    }

}
