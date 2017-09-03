package name.msutherland.config;

import name.msutherland.jdbc.SectionDAO;
import name.msutherland.jdbc.VenueDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

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

    public static void stop() {
        applicationContext.stop();
    }

    @Bean
    public VenueDAO getVenueDAO(DataSource dataSource){
        return new VenueDAO(new JdbcTemplate(dataSource));
    }
    @Bean
    public SectionDAO getSectionDAO(DataSource dataSource){
        return new SectionDAO(new JdbcTemplate(dataSource));
    }
}
