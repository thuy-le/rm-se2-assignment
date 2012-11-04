package devfortress.models;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Team Poseidon
 */
public class DevDateTest {
    
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
     * Test of getMonth method, of class DevDate.
     */
    @Test
    public void testGetMonth() {
        System.out.println("getMonth");
        DevDate instance = new DevDate();
        int expResult = 0;
        int result = instance.getMonth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWeek method, of class DevDate.
     */
    @Test
    public void testGetWeek() {
        System.out.println("getWeek");
        DevDate instance = new DevDate();
        int expResult = 0;
        int result = instance.getWeek();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getYear method, of class DevDate.
     */
    @Test
    public void testGetYear() {
        System.out.println("getYear");
        DevDate instance = new DevDate();
        int expResult = 0;
        int result = instance.getYear();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextWeek method, of class DevDate.
     */
    @Test
    public void testNextWeek() {
        System.out.println("nextWeek");
        DevDate instance = new DevDate();
        instance.nextWeek();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
