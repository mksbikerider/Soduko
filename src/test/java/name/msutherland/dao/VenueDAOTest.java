package name.msutherland.dao;

import name.msutherland.config.SpringStart;
import name.msutherland.dto.Venue;
import name.msutherland.jdbc.VenueDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringStart.class})
public class VenueDAOTest {
    @Autowired
    DataSource ds;

    @Test
    public void testDataAccess() {
        JdbcTemplate template = new JdbcTemplate(ds);
        VenueDAO venueDAO = new VenueDAO(template);
        List<Venue> result = venueDAO.getVenues();
        assertEquals(result.size(),2);
        // Continue testing with hamcrest matchers...
    }
}
