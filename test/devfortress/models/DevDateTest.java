package devfortress.models;

import devfortress.models.exceptions.InvalidDevDateException;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * Unit test for <code>{@link DevDate}</code>.
 * @author Team Poseidon
 */
public class DevDateTest {

    DevDate testDate;

    public DevDateTest() {
    }

    /**
     * Test of contructor in class DevDate.
     * Test case 1: Create a valid DevDate instance.
     */
    @Test
    public void testContructor_1() {
        try {
            testDate = new DevDate(3, 11, 2);
            assertEquals(testDate.getYear(), 3);
            assertEquals(testDate.getMonth(), 11);
            assertEquals(testDate.getWeek(), 2);
        } catch (InvalidDevDateException ex) {
        }
    }

    /**
     * Test of contructor in class DevDate.
     * Test case 2: Create a invalid DevDate instance. (month = 13 (> 12))
     */
    @Test(expected = InvalidDevDateException.class)
    public void testContructor_2() throws InvalidDevDateException {
        testDate = new DevDate(3, 13, 2);
    }

    /**
     * Test of nextWeek method, of class DevDate.
     * Test case 1: Using default contructor of <code>{@link DevDate}</code>
     */
    @Test
    public void testNextWeek_1() {
        testDate = new DevDate();
        testDate.nextWeek();
        assertEquals(testDate.getWeek(), 2);
    }

    /**
     * Test of nextWeek method, of class DevDate.
     * Test case 2: testDate is set at the end of the month (week = 3)
     */
    @Test
    public void testNextWeek_2() {
        try {
            testDate = new DevDate(0, 1, 4);
        } catch (InvalidDevDateException ex) {
        }
        testDate.nextWeek();
        assertEquals(testDate.getWeek(), 1);
        assertEquals(testDate.getMonth(), 2);
    }

    /**
     * Test of nextWeek method, of class DevDate.
     * Test case 3: testDate is set at the end of the month (week = 3, month = 11)
     */
    @Test
    public void testNextWeek_3() {
        try {
            testDate = new DevDate(0, 12, 4);
        } catch (InvalidDevDateException ex) {
        }
        testDate.nextWeek();
        assertEquals(testDate.getWeek(), 1);
        assertEquals(testDate.getMonth(), 1);
        assertEquals(testDate.getYear(), 1);
    }

    /**
     * Test of addMonths method, of class DevDate.
     * Test case 1: Add 1 month, from 1/1/0
     */
    @Test
    public void testAddMonths_1() {
        try {
            testDate = new DevDate(0, 1, 1);
            DevDate otherDate = testDate.addMonths(1);
            assertEquals(0, otherDate.getYear());
            assertEquals(2, otherDate.getMonth());
            assertEquals(1, otherDate.getWeek());
        } catch (InvalidDevDateException ex) {
        }
    }

    /**
     * Test of addMonths method, of class DevDate.
     * Test case 2: Add 11 month, from 1/1/0
     */
    @Test
    public void testAddMonths_2() {
        try {
            testDate = new DevDate(0, 1, 1);
            DevDate otherDate = testDate.addMonths(11);
            assertEquals(0, otherDate.getYear());
            assertEquals(12, otherDate.getMonth());
            assertEquals(1, otherDate.getWeek());
        } catch (InvalidDevDateException ex) {
        }
    }

    /**
     * Test of addMonths method, of class DevDate.
     * Test case 2: Add 1 month, from 1/12/0
     */
    @Test
    public void testAddMonths_3() {
        try {
            testDate = new DevDate(0, 12, 1);
            DevDate otherDate = testDate.addMonths(1);
            assertEquals(1, otherDate.getYear());
            assertEquals(1, otherDate.getMonth());
            assertEquals(1, otherDate.getWeek());
        } catch (InvalidDevDateException ex) {
        }
    }

    /**
     * Test of addMonths method, of class DevDate.
     * Test case 2: Add 0 month, from 1/1/0
     */
    @Test
    public void testAddMonths_4() {
        try {
            testDate = new DevDate(0, 1, 1);
            DevDate otherDate = testDate.addMonths(0);
            assertEquals(0, otherDate.getYear());
            assertEquals(1, otherDate.getMonth());
            assertEquals(1, otherDate.getWeek());
        } catch (InvalidDevDateException ex) {
        }
    }

    /**
     * Test of addMonths method, of class DevDate.
     * Test case 2: Add -1 month
     */
    @Test(expected = InvalidDevDateException.class)
    public void testAddMonths_5() throws InvalidDevDateException {
        testDate = new DevDate(0, 1, 1);
        DevDate otherDate = testDate.addMonths(-1);
        assertEquals(0, otherDate.getYear());
        assertEquals(12, otherDate.getMonth());
        assertEquals(1, otherDate.getWeek());
    }
}
