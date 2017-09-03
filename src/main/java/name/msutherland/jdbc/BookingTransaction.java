package name.msutherland.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import java.util.List;
import java.util.UUID;

/**
 * Created by michaelsutherland on 03/09/2017.
 */
public class BookingTransaction {

    // single TransactionTemplate shared amongst all methods in this instance
    private final TransactionTemplate transactionTemplate;
    private final SeatResevationDAO seatResevationDAO;

    private final JdbcTemplate jdbcTemplate;

    // use constructor-injection to supply the PlatformTransactionManager
    public BookingTransaction(PlatformTransactionManager transactionManager, SeatResevationDAO seatResevationDAO, JdbcTemplate jdbcTemplate) {
        this.seatResevationDAO = seatResevationDAO;
        this.jdbcTemplate = jdbcTemplate;
        Assert.notNull(transactionManager, "The 'transactionManager' argument must not be null.");
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }

    public String bookSeats(List<Integer> seatIds, String bookingDescription){
        // the code in this method executes in a transactional context
        return transactionTemplate.execute( new TransactionCallback<String>() {
            // the code in this method executes in a transactional context
            public String doInTransaction(TransactionStatus status) {
                // Could skip this and have the unique constraint in the DB throw an error
                Integer numberOfResevedSeats = seatResevationDAO.getReservations(seatIds);
                if (numberOfResevedSeats > 0) {
                    throw new RuntimeException("Seats already reserved");
                }

                // Should check the seats actually exists...

                // else
                return bookSeats(bookingDescription, seatIds);
            }
        });
    }

    private String bookSeats(String bookingDescription, List<Integer> seatIds) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        jdbcTemplate.update("INSERT INTO reservation (ID, description) VALUES (?,?)", new Object[]{randomUUIDString, bookingDescription});
        for(int seatId:  seatIds) {
            jdbcTemplate.update("INSERT INTO reservation_to_seat (RESERVATION_ID, SEAT_ID) VALUES (?,?)", new Object[]{randomUUIDString, seatId});
        }

        return randomUUIDString;
    }

    public String bookSeatsInRow(Integer sectionId, Integer numberOfSeats, String bookingDescription){
        // the code in this method executes in a transactional context
        return transactionTemplate.execute( new TransactionCallback<String>() {
            // the code in this method executes in a transactional context
            public String doInTransaction(TransactionStatus status) {
                List<Integer> seatIds = seatResevationDAO.getUnreservedSeats(sectionId,numberOfSeats);
                return bookSeats(bookingDescription, seatIds);
            }
        });
    }
}
