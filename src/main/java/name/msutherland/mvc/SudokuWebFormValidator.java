package name.msutherland.mvc;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SudokuWebFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(SudokuWebForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SudokuWebForm form = (SudokuWebForm) target;
        for(int i = 0 ; i < 9; i++){
            for(int j = 0 ; j < 9 ; j++) {
                if (form.getMatrix()[i][j] != null && !form.getMatrix()[i][j].isEmpty()) {
                    try {
                        int n = Integer.parseInt(form.getMatrix()[i][j]);
                        if (n < 1) {
                            errors.rejectValue("matrix[" + i + "][" + j + "]", "TooSmall","Too small");
                        } else if (n > 9) {
                            errors.rejectValue("matrix[" + i + "][" + j + "]", "TooBig","Too big");
                        }
                    } catch (NumberFormatException e) {
                        errors.rejectValue("matrix[" + i + "][" + j + "]", "NotANumber","Not a number");
                    }
                }
            }
        }

    }
}
