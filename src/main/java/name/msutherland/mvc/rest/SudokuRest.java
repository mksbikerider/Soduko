package name.msutherland.mvc.rest;

import name.msutherland.LoopSolve;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SudokuRest {

    @RequestMapping(path="/rest", method=RequestMethod.GET)
    public SudokuRestMatrix home() {
        return new SudokuRestMatrix();
    }

    @RequestMapping(path="/rest", method= RequestMethod.POST)
    public ModelMap solve(@Valid SudokuRestMatrix form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelMap result = new ModelMap();
            result.mergeAttributes(bindingResult.getModel());
            return result;
        }
       Integer[][] matrixInt = new Integer[9][9];
        int i = 0;
        for(SudokuRestRow row : form.getRows()){
            int j = 0;
            for(String cell : row.getCells()) {
                if (cell != null && !cell.isEmpty()) {
                    matrixInt[i][j] = Integer.valueOf(cell);
                }else{
                    matrixInt[i][j] = 0;
                }
                j++;
            }
            i++;
        }
        int size = new LoopSolve(1000).solve(matrixInt).size();

        ModelMap result = new ModelMap();
        result.addAttribute("size", size);
        return result;
    }
}
