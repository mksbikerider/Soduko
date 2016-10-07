package name.msutherland;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class ParallelSolve extends StreamSolve {

    Stream<Integer> streamValidValues(){
        return VALID_VALUES.parallelStream();
    }

    Function<List<Integer[][]>, Stream<Integer[][]>> mapper(){
        return List::parallelStream;
    }
}
