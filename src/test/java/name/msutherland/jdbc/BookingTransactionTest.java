package name.msutherland.jdbc;

import name.msutherland.config.SpringStart;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringStart.class})
public class BookingTransactionTest {
    @Autowired
    BookingTransaction bookingTransaction;

    @Test
    public void testBookSeats() {
        bookingTransaction.bookSeats(Arrays.asList(3,4), "testDescription");
    }

    @Test
    public void testBookedSeats() {
        try {
            bookingTransaction.bookSeats(Arrays.asList(1, 2, 3), "testDescription");
            fail("Exceptions should have been thrown");
        }catch(RuntimeException e){
            assertThat(e.getMessage(), CoreMatchers.containsString("Seats already reserved"));
        }
    }

    @Test
    public void testBookSeatsInRow() {
        bookingTransaction.bookSeatsInRow(2, 1, "Example string");
    }

    @Test
    public void testBookTooManySeatsInRow() {
        try {
            bookingTransaction.bookSeatsInRow(2, 20, "Example string");
        }catch (RuntimeException e) {
            assertThat(e.getMessage(), CoreMatchers.containsString("No row with enough free seats"));
        }
    }
}