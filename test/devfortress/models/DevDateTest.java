package devfortress.models;

import devfortress.models.exceptions.InvalidDevDateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.*;

/**
 * Unit test for <code>{@link DevDate}</code>.
 * @author Team Poseidon
 */
public class DevDateTest {
    
    DevDate testDate;
    
    public DevDateTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getMonth method, of class DevDate. (Ignored)
     */
    @Ignore
    @Test
    public void testGetMonth() {
        System.out.println("getMonth");
        DevDate instance = new DevDate();
        int expResult = 0;
        int result = instance.getMonth();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWeek method, of class DevDate. (Ignored)
     */
    @Ignore
    @Test
    public void testGetWeek() {
        System.out.println("getWeek");
        DevDate instance = new DevDate();
        int expResult = 0;
        int result = instance.getWeek();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getYear method, of class DevDate. (Ignored)
     */
    @Ignore
    @Test
    public void testGetYear() {
        System.out.println("getYear");
        DevDate instance = new DevDate();
        int expResult = 0;
        int result = instance.getYear();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
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
            Logger.getLogger(DevDateTest.class.getName()).log(Level.SEVERE, null, ex);
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
        assertEquals(testDate.getWeek(), 1);
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
            Logger.getLogger(DevDateTest.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DevDateTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        testDate.nextWeek();
        assertEquals(testDate.getWeek(), 1);
        assertEquals(testDate.getMonth(), 1);
        assertEquals(testDate.getYear(), 1);
    }
}
