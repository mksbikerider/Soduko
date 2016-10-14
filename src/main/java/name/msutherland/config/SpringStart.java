package name.msutherland.config;


import name.msutherland.mvc.SudokuWebFormValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@SpringBootApplication
@ComponentScan("name.msutherland")
public class SpringStart extends SpringBootServletInitializer {

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new SudokuWebFormValidator());
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringStart.class, args);
    }
}
