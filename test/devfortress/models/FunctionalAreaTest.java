package devfortress.models;

import devfortress.enumerations.AreaName;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Team Poseidon
 */
public class FunctionalAreaTest {
    
    public FunctionalAreaTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDevelopers method, of class FunctionalArea.
     */
    @Test
    public void testGetDevelopers() {
        System.out.println("getDevelopers");
        FunctionalArea instance = null;
        Set expResult = null;
        Set result = instance.getDevelopers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFunctionPoints method, of class FunctionalArea.
     */
    @Test
    public void testGetFunctionPoints() {
        System.out.println("getFunctionPoints");
        FunctionalArea instance = null;
        int expResult = 0;
        int result = instance.getFunctionPoints();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class FunctionalArea.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        FunctionalArea instance = null;
        AreaName expResult = null;
        AreaName result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReducedPoints method, of class FunctionalArea.
     */
    @Test
    public void testGetReducedPoints() {
        System.out.println("getReducedPoints");
        FunctionalArea instance = null;
        int expResult = 0;
        int result = instance.getReducedPoints();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompletedPoints method, of class FunctionalArea.
     */
    @Test
    public void testGetCompletedPoints() {
        System.out.println("getCompletedPoints");
        FunctionalArea instance = null;
        int expResult = 0;
        int result = instance.getCompletedPoints();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isVisible method, of class FunctionalArea.
     */
    @Test
    public void testIsVisible() {
        System.out.println("isVisible");
        FunctionalArea instance = null;
        boolean expResult = false;
        boolean result = instance.isVisible();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCompleted method, of class FunctionalArea.
     */
    @Test
    public void testIsCompleted() {
        System.out.println("isCompleted");
        FunctionalArea instance = null;
        boolean expResult = false;
        boolean result = instance.isCompleted();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addFunctionPoints method, of class FunctionalArea.
     */
    @Test
    public void testAddFunctionPoints() {
        System.out.println("addFunctionPoints");
        int points = 0;
        FunctionalArea instance = null;
        instance.addFunctionPoints(points);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of progress method, of class FunctionalArea.
     */
    @Test
    public void testProgress_0args() {
        System.out.println("progress");
        FunctionalArea instance = null;
        instance.progress();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of progress method, of class FunctionalArea.
     */
    @Test
    public void testProgress_int() {
        System.out.println("progress");
        int points = 0;
        FunctionalArea instance = null;
        instance.progress(points);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reducePoints method, of class FunctionalArea.
     */
    @Test
    public void testReducePoints() {
        System.out.println("reducePoints");
        int points = 0;
        FunctionalArea instance = null;
        instance.reducePoints(points);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addDeveloper method, of class FunctionalArea.
     */
    @Test
    public void testAddDeveloper() {
        System.out.println("addDeveloper");
        Developer dev = null;
        FunctionalArea instance = null;
        boolean expResult = false;
        boolean result = instance.addDeveloper(dev);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeDeveloper method, of class FunctionalArea.
     */
    @Test
    public void testRemoveDeveloper() {
        System.out.println("removeDeveloper");
        Developer dev = null;
        FunctionalArea instance = null;
        instance.removeDeveloper(dev);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class FunctionalArea.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        FunctionalArea instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAllDeveloper method, of class FunctionalArea.
     */
    @Test
    public void testRemoveAllDeveloper() {
        System.out.println("removeAllDeveloper");
        FunctionalArea instance = null;
        instance.removeAllDeveloper();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
