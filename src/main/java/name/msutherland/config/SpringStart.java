package name.msutherland.config;

import name.msutherland.jdbc.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@SpringBootApplication
@ComponentScan("name.msutherland")
public class SpringStart extends SpringBootServletInitializer {
    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) throws Exception {
        start(args);
    }

    public static void start(String[] args) {
        applicationContext = SpringApplication.run(SpringStart.class, args);
    }

    public static void stop() {
        applicationContext.stop();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public VenueDAO getVenueDAO(JdbcTemplate jdbcTemplate) {
        return new VenueDAO(jdbcTemplate);
    }

    @Bean
    public SectionDAO getSectionDAO(JdbcTemplate jdbcTemplate) {
        return new SectionDAO(jdbcTemplate);
    }

    @Bean
    public SectionRowDAO getSectionRowDAO(JdbcTemplate jdbcTemplate) {
        return new SectionRowDAO(jdbcTemplate);
    }

    @Bean
    public SeatResevationDAO getSeatResevationDAO(DataSource dataSource, SectionRowDAO sectionRowDAO) {
        return new SeatResevationDAO(new NamedParameterJdbcTemplate(dataSource), sectionRowDAO);
    }

    @Bean
    public BookingTransaction getBookingTransaction(PlatformTransactionManager transactionManager, SeatResevationDAO seatResevationDAO, JdbcTemplate jdbcTemplate) {
        return new BookingTransaction(transactionManager, seatResevationDAO ,jdbcTemplate);
    }
}
