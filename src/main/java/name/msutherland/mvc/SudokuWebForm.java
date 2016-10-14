package name.msutherland.mvc;


public class SudokuWebForm{
    private String[][] matrix = {new String[9],new String[9],new String[9],new String[9],new String[9],
            new String[9],new String[9],new String[9],new String[9]};

    public String[][] getMatrix(){
        return matrix;
    }

    public void setMatrix(String[][] matrix) {
        this.matrix = matrix;
    }
}
