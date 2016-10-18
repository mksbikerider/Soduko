package name.msutherland;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LoopSolve implements Solve{

    private final int maxDepth;

    public LoopSolve() {
        this(0);
    }

    public LoopSolve(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public List<Integer[][]> solve(Integer[][] cells){
        return solve(0,0,cells);
    }

    private List<Integer[][]> solve(int i, int j, Integer[][] cells) {
        if (i == 9 && j == 8) {
            // All squares filled, valid grid
            return Collections.singletonList(cells);
        }
        if (i == 9) { // Go to next line
            i = 0;
            ++j;
        }
        if (cells[i][j] != 0) {  // skip filled cells
            return solve(i + 1, j, cells);
        }

        LegalSudokuEntry legal = new LegalSudokuEntry(i, j, cells);
        List<Integer[][]> results = new ArrayList<>();
        for (int val = 1; val <= 9; ++val) {
            if (legal.test(val)) {
                Integer[][] cellsCopy = Sudoku.deepCopy(cells);
                cellsCopy[i][j] = val;
                results.addAll(solve(i + 1, j, cellsCopy));
                if(results.size()>maxDepth){
                    return results;
                }
            }
        }
        return results;
    }
}
