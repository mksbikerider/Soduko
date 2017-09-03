package name.msutherland.jdbc;

import name.msutherland.dto.Section;
import name.msutherland.dto.Venue;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SectionDAO {
    private final JdbcTemplate jdbcTemplate;

    public SectionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Section> getSections(int venueId){
        return jdbcTemplate.query("select ID, name from sections where venue_id = ?",new Object[] {venueId} ,  new SectionMapper());
    }

}
