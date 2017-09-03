package name.msutherland.jdbc;

import name.msutherland.dto.SectionRow;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SectionRowMapper implements RowMapper<SectionRow> {

    public SectionRow mapRow(ResultSet rs, int rowNum) throws SQLException {
        SectionRow section = new SectionRow(rs.getInt("id"));
        return section;
    }
}