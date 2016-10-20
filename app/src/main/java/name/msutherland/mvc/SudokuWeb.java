package name.msutherland.mvc;

import name.msutherland.LoopSolve;
import org.springframework.stereotype.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class SudokuWeb{

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new SudokuWebFormValidator());
    }
    @RequestMapping(path="/", method=RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView result = new ModelAndView("HomeView");
        result.addObject("sudokuWebForm", new SudokuWebForm());
        return result;
    }

    @RequestMapping(path="/", method=RequestMethod.POST)
    public ModelAndView solve(@Valid SudokuWebForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView result = new ModelAndView("HomeView");
            result.addAllObjects(bindingResult.getModel());
            return result;
        }
        Integer[][] matrixInt = new Integer[9][9];
        for(int i = 0 ; i < 9; i++){
            for(int j = 0 ; j < 9 ; j++) {
                if (form.getMatrix()[i][j] != null && !form.getMatrix()[i][j].isEmpty()) {
                    matrixInt[i][j] = Integer.valueOf(form.getMatrix()[i][j]);
                }else{
                    matrixInt[i][j] = 0;
                }

            }
        }
        int size = new LoopSolve(1000).solve(matrixInt).size();

        ModelAndView result = new ModelAndView("ResultView");
        result.addObject("size", size);
        return result;
    }

}
