package name.msutherland.jdbc;

import name.msutherland.dto.Section;
import name.msutherland.dto.Venue;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SectionMapper implements RowMapper<Section> {

    public Section mapRow(ResultSet rs, int rowNum) throws SQLException {
        Section section = new Section(rs.getInt("id"), rs.getString("name"));
        return section;
    }
}