package devfortress.models;

import devfortress.enumerations.AreaName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Team Poseidon
 */
public class FunctionalAreaTest {

    private FunctionalArea testObject;
    private Developer dev1, dev2;

    public FunctionalAreaTest() {
    }

    @Before
    public void setUp() {
        testObject = new FunctionalArea(AreaName.CODING, 100, 0, true);
        dev1 = mock(Developer.class);
        when(dev1.getLastWeekFunctionPoints()).thenReturn(10);
        when(dev1.isAvailable()).thenReturn(true);
        dev2 = mock(Developer.class);
        when(dev2.getLastWeekFunctionPoints()).thenReturn(15);
        when(dev2.isAvailable()).thenReturn(true);
        testObject.addDeveloper(dev1);
        testObject.addDeveloper(dev2);
    }

    @After
    public void tearDown() {
        testObject = null;
        dev1 = null;
        dev2 = null;
    }

    /**
     * Test of progress method, of class FunctionalArea.
     */
    @Test
    public void testProgress_0args_1() {
        testObject.progress();
        int expected = dev1.getLastWeekFunctionPoints() + dev2.getLastWeekFunctionPoints();
        assertEquals(expected, testObject.getCompletedPoints());
    }

    /**
     * Test of progress method, of class FunctionalArea.
     */
    @Test
    public void testProgress_0args_2() {
        while (testObject.isCompleted() == false) {
            testObject.progress();
        }
        int expected = testObject.getFunctionPoints() - testObject.getReducedPoints();
        assertEquals(expected, testObject.getCompletedPoints());
    }

    /**
     * Test of progress method, of class FunctionalArea.
     */
    @Test
    public void testProgress_int_1() {
        testObject.progress(20);
        int expected = 20;
        assertEquals(expected, testObject.getCompletedPoints());
    }

    /**
     * Test of progress method, of class FunctionalArea.
     */
    @Test
    public void testProgress_int_2() {
        testObject.progress(testObject.getFunctionPoints());
        int expected = testObject.getFunctionPoints();
        assertEquals(expected, testObject.getCompletedPoints());
    }

    /**
     * Test of progress method, of class FunctionalArea.
     */
    @Test
    public void testProgress_int_3() {
        testObject.progress(testObject.getFunctionPoints() + 1);
        int expected = testObject.getFunctionPoints();
        assertEquals(expected, testObject.getCompletedPoints());
    }

    /**
     * Test of addDeveloper method, of class FunctionalArea.
     */
    @Test
    public void testAddDeveloper_1() {
        Developer dev3 = mock(Developer.class);
        when(dev3.isAvailable()).thenReturn(true);
        assertTrue(testObject.addDeveloper(dev3));
        assertEquals(3, testObject.getDevelopers().size());
    }

    /**
     * Test of addDeveloper method, of class FunctionalArea.
     */
    @Test
    public void testAddDeveloper_2() {
        Developer dev3 = mock(Developer.class);
        when(dev3.isAvailable()).thenReturn(false);
        assertFalse(testObject.addDeveloper(dev3));
        assertEquals(2, testObject.getDevelopers().size());
    }

    /**
     * Test of removeDeveloper method, of class FunctionalArea.
     */
    @Test
    public void testRemoveDeveloper_1() {
        testObject.removeDeveloper(dev1);
        assertEquals(1, testObject.getDevelopers().size());
    }

    /**
     * Test of removeDeveloper method, of class FunctionalArea.
     */
    @Test
    public void testRemoveDeveloper_2() {
        Developer dev3 = mock(Developer.class);
        testObject.removeDeveloper(dev3);
        assertEquals(2, testObject.getDevelopers().size());
    }

    /**
     * Test of removeAllDeveloper method, of class FunctionalArea.
     */
    @Test
    public void testRemoveAllDeveloper() {
        testObject.removeAllDeveloper();
        assertEquals(0, testObject.getDevelopers().size());
    }
}
