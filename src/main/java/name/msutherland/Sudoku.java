package name.msutherland;

import com.codahale.metrics.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * Copied from https://bob-carpenter.github.io/games/sudoku/java_sudoku.html
 * Using Sudoku as a way to test new type of code
 *
 * Change code to find all possible solutions not just the first.
 */
public class Sudoku {

    static final MetricRegistry metrics = new MetricRegistry();
    static ConsoleReporter reporter;
    static final Timer main = metrics.timer(MetricRegistry.name(Sudoku.class, "main"));

    /**
     * Print the specified Sudoku problem and its solution.  The
     * problem is encoded as specified in the class documentation
     * above.
     *
     * @param args The command-line arguments encoding the problem.
     */
    public static void main(String[] args) {
        startReport();
        //int[][] matrix = parseProblem(args);
        int[][] matrix = GRID_OF_68_SOLUTIONS;
        writeMatrix(matrix);
        Solve solver = new StreamSolve();
        for (int i = 0 ; i < 100 ; i ++)
        {
            Timer.Context context = main.time();
            solver.solve(deepCopy(matrix));
            context.stop();
        }
        List results = solver.solve(matrix);

        reporter.report();
        if (!results.isEmpty())    // solves in place
            System.out.println("Solutions : "+results.size());
        else
            System.out.println("NONE");
    }

    static int[][] deepCopy(int[][] matrix) {
        return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
    }

    static void startReport() {
        reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);
    }

    static boolean legal(int i, int j, int val, int[][] cells) {
        LegalSudokuEntry legal = new LegalSudokuEntry(i, j, cells);
        return legal.test(val);
    }

    static int[][] parseProblem(String[] args) {
        int[][] problem = new int[9][9]; // default 0 vals
        for (int n = 0; n < args.length; ++n) {
            int i = Integer.parseInt(args[n].substring(0,1));
            int j = Integer.parseInt(args[n].substring(1,2));
            int val = Integer.parseInt(args[n].substring(2,3));
            problem[i][j] = val;
        }
        return problem;
    }

    private static final int[][] GRID_OF_16320_SOLUTIONS ={
            {0,0,0,2,3,8,5,6,9},
            {0,0,0,1,6,9,3,4,7},
            {0,0,0,4,5,7,1,2,8},
            {4,7,1,0,0,0,6,9,5},
            {5,8,2,0,0,0,4,7,3},
            {6,9,3,0,0,0,2,8,1},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static final int[][] GRID_OF_68_SOLUTIONS ={
            {0,0,0,2,3,8,5,6,9},
            {0,0,0,1,6,9,3,4,7},
            {0,0,0,4,5,7,1,2,8},
            {4,7,1,0,0,0,6,9,5},
            {5,8,2,0,0,0,4,7,3},
            {6,9,3,0,0,0,2,8,1},
            {7,1,4,8,2,3,9,5,6},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static final int[][] LAST_MISSING_GRID ={
            {1,4,7,2,3,8,5,6,9},
            {2,5,8,1,6,9,3,4,7},
            {3,6,9,4,5,7,1,2,8},
            {4,7,1,3,8,2,6,9,5},
            {5,8,2,6,9,1,4,7,3},
            {6,9,3,5,7,4,2,8,1},
            {7,1,4,8,2,3,9,5,6},
            {8,2,5,9,1,6,7,3,4},
            {9,3,6,7,4,5,8,1,0}
    };

    static void writeMatrix(int[][] solution) {
        for (int i = 0; i < 9; ++i) {
            if (i % 3 == 0)
                System.out.println(" -----------------------");
            for (int j = 0; j < 9; ++j) {
                if (j % 3 == 0) System.out.print("| ");
                System.out.print(solution[i][j] == 0
                        ? " "
                        : Integer.toString(solution[i][j]));

                System.out.print(' ');
            }
            System.out.println("|");
        }
        System.out.println(" -----------------------");
    }

}
