package name.msutherland.jdbc;

import name.msutherland.dto.Venue;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class VenueMapper implements RowMapper<Venue> {

    public Venue mapRow(ResultSet rs, int rowNum) throws SQLException {
        Venue venue = new Venue(rs.getInt("id"), rs.getString("name"));
        return venue;
    }
}