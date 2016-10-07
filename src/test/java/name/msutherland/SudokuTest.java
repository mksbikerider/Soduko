package name.msutherland;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import org.junit.jupiter.api.Test;

@RunWith(JUnitPlatform.class)
public class SudokuTest {

    private static final int[][] EMPTY_GRID ={
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    @Test
    public void testLegalMoveInEmptyGrid(){
        assertEquals(true, Sudoku.legal(0,0,1,EMPTY_GRID));
    }

    @Test
    public void testIllegalMoveInEmptyGrid(){
        assertEquals(false, Sudoku.legal(0,0,0,EMPTY_GRID));
    }

    private static final int[][] SET_GRID ={
            {0,2,3,4,5,6,7,8,9},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    @Test
    public void testLegalMoveInSetGrid(){
        assertEquals(true, Sudoku.legal(0,0,1,SET_GRID));
    }

    @Test
    public void testIllegalMoveInSetGrid(){
        assertEquals(false, Sudoku.legal(0,0,2,SET_GRID));
    }
}
