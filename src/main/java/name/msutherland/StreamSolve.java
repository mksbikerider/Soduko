package name.msutherland;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StreamSolve implements Solve, Function<Integer[][], List<Integer[][]> >{

    static final List<Integer> VALID_VALUES = Arrays.asList(1,2,3,4,5,6,7,8,9);

    private int i;
    private int j;

    public StreamSolve(){
        this(0,0);
    }

    private StreamSolve(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public List<Integer[][]> solve(Integer[][] cells){

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

        return streamValidValues().unordered().filter(legal)
                .map(createMatrix)
                .map(solve).flatMap(mapper()).collect(Collectors.toList());

    }

    Stream<Integer> streamValidValues(){
        return VALID_VALUES.stream();
    }

    Function<List<Integer[][]>, Stream<Integer[][]>> mapper(){
        return List::stream;
    }

    @Override
    public List<Integer[][]> apply(Integer[][] cells) {
        return solve(cells);
    }

}
