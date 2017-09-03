package name.msutherland;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import org.junit.jupiter.api.Test;

@RunWith(JUnitPlatform.class)
public class LegalSudokuEntryTest {

    private static final Integer[][] EMPTY_GRID ={
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
        assertEquals(true, new LegalSudokuEntry(0,0,EMPTY_GRID).test(1));
    }

    @Test
    public void testIllegalMoveInEmptyGrid(){
        assertEquals(false, new LegalSudokuEntry(0,0,EMPTY_GRID).test(0));
    }

    private static final Integer[][] SET_GRID ={
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
        assertEquals(true, new LegalSudokuEntry(0,0,SET_GRID).test(1));
    }

    @Test
    public void testIllegalMoveInSetGrid(){
        assertEquals(false, new LegalSudokuEntry(0,0,SET_GRID).test(2));
    }
}
