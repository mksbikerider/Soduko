package name.msutherland.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("name.msutherland")
public class SpringStart extends SpringBootServletInitializer {
    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) throws Exception {
        start(args);
    }

    public static void start(String[] args){
        applicationContext = SpringApplication.run(SpringStart.class, args);
    }

    public static void stop(){
        applicationContext.stop();
    }
}
