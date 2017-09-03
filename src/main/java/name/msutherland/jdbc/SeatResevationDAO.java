package name.msutherland.jdbc;

import name.msutherland.dto.SectionRow;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;


public class SeatResevationDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SectionRowDAO sectionRowDAO;

    public SeatResevationDAO(NamedParameterJdbcTemplate jdbcTemplate, SectionRowDAO sectionRowDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.sectionRowDAO = sectionRowDAO;
    }

    Integer getReservations(List<Integer> seatIds){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("seatIds", seatIds);
        return jdbcTemplate.queryForObject("select count(SEAT_ID) from reservation_to_seat where SEAT_ID IN (:seatIds)",parameters, Integer.class);
    }

    List<Integer> getUnreservedSeats(Integer sectionId, Integer numberOfSeats){

        for(SectionRow row : sectionRowDAO.getSectionRows(sectionId)) {
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("section_row_id", row.getId());
            String query = "select seats.id from seats " +
                    "left outer join reservation_to_seat on seats.id = reservation_to_seat.SEAT_ID "
                    + "where seats.SECTION_ROW_ID = (:section_row_id)"
                    + "and  reservation_to_seat.RESERVATION_ID is null";
            // Should make the SQL clever to make sure seats are contiguous
            List<Integer> unreservedSeats = jdbcTemplate.queryForList(query, parameters, Integer.class);
            if(unreservedSeats.size()>=numberOfSeats){
                unreservedSeats.subList(0,numberOfSeats);
                return unreservedSeats;
            }
        }
        throw new RuntimeException("No row with enough free seats");
    }




}
