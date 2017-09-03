package name.msutherland.jdbc;

import name.msutherland.dto.Venue;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class VenueDAO {
    private final JdbcTemplate jdbcTemplate;

    public VenueDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Venue> getVenues(){
        return jdbcTemplate.query("select ID, name from venues", new VenueMapper());
    }

}
