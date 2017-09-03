package name.msutherland.jdbc;

import name.msutherland.dto.SectionRow;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SectionRowDAO {
    private final JdbcTemplate jdbcTemplate;

    public SectionRowDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SectionRow> getSectionRows(int sectionId){
        return jdbcTemplate.query("select ID from section_rows where section_id = ?",new Object[] {sectionId} ,  new SectionRowMapper());
    }

}
