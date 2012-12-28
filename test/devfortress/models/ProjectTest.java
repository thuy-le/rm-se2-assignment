package devfortress.models;

import devfortress.models.exceptions.DeveloperBusyException;
import devfortress.models.exceptions.InvalidFunctionalAreaException;
import devfortress.enumerations.AreaName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 *
 * @author Team Poseidon
 */
public class ProjectTest {

    private Project testObject;
    private FunctionalArea area1, area2;
    private Developer dev1, dev2;

    public ProjectTest() {
    }

    @Before
    public void setUp() {
        testObject = new Project();
    }

    @After
    public void tearDown() {
        testObject.clearEvents();
        for (AreaName area : AreaName.values()) {
            testObject.removeFunctionalArea(area);
        }
    }

    /**
     * Test of addDeveloper method, of class Project.
     */
    @Test
    public void testAddDeveloper() throws Exception {
        System.out.println("addDeveloper");
        Developer dev = mock(Developer.class);
        AreaName area = (AreaName) testObject.getAreas().keySet().toArray()[0];
        testObject.addDeveloper(dev, area);
        dev = mock(Developer.class);
        testObject.addDeveloper(dev, area);
        dev = mock(Developer.class);
        testObject.addDeveloper(dev, area);
        dev = mock(Developer.class);
        testObject.addDeveloper(dev, area);
        assertEquals(testObject.getDevelopers().size(), 4);
    }

    @Test
    public void testAddDeveloper2() throws Exception {
        System.out.println("addDeveloper");
        Developer dev = mock(Developer.class);
        AreaName area = (AreaName) testObject.getAreas().keySet().toArray()[0];
        testObject.addDeveloper(dev, area);
        dev = new Developer();
        testObject.addDeveloper(dev, area);
        try {
            testObject.addDeveloper(dev, area);
        } catch (DeveloperBusyException developerBusyException) {
        } catch (InvalidFunctionalAreaException invalidFunctionalAreaException) {
        }
        dev = mock(Developer.class);
        testObject.addDeveloper(dev, area);
        assertEquals(testObject.getDevelopers().size(), 3);
    }

    @Test
    public void testAddDeveloper3() throws Exception {
        System.out.println("addDeveloper");
        Developer dev = mock(Developer.class);
        AreaName area = (AreaName) testObject.getAreas().keySet().toArray()[0];
        testObject.addDeveloper(dev, area);
        dev = mock(Developer.class);
        testObject.addDeveloper(dev, area);
        dev = mock(Developer.class);
        try {
            testObject.addDeveloper(dev, null);
        } catch (DeveloperBusyException developerBusyException) {
        } catch (InvalidFunctionalAreaException invalidFunctionalAreaException) {
        }
        assertEquals(testObject.getDevelopers().size(), 2);
    }

    /**
     * Test of removeDeveloper method, of class Project.
     */
    @Test
    public void testRemoveDeveloper() throws Exception {
        System.out.println("removeDeveloper");
        Developer d1 = mock(Developer.class);
        AreaName area = (AreaName) testObject.getAreas().keySet().toArray()[0];
        when(d1.getWorkingArea()).thenReturn(area);
        testObject.addDeveloper(d1, area);
        Developer d2 = mock(Developer.class);
        when(d2.getWorkingArea()).thenReturn(area);
        testObject.addDeveloper(d2, area);
        Developer d3 = mock(Developer.class);
        when(d3.getWorkingArea()).thenReturn(area);
        testObject.addDeveloper(d3, area);
        Developer d4 = mock(Developer.class);
        when(d4.getWorkingArea()).thenReturn(area);
        testObject.addDeveloper(d4, area);
        testObject.removeDeveloper(d2);
        testObject.removeDeveloper(d1);
        assertEquals(2, testObject.getDevelopers().size());
    }

    /**
     * Test of removeFunctionalArea method, of class Project.
     */
    @Test
    public void testRemoveFunctionalArea() {
        System.out.println("removeFunctionalArea");
        AreaName area = (AreaName) testObject.getAreas().keySet().toArray()[0];
        testObject.removeFunctionalArea(area);
        assertEquals(null, testObject.getAreas().get(area));
    }

    /**
     * Test of progress method, of class Project.
     */
    @Test
    public void testProgress() {
        System.out.println("progress");
        DevDate date = null;
        Project instance = new Project();
        instance.progress(date);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAllEvents method, of class Project.
     */
    @Test
    public void testRemoveAllEvents() {
        System.out.println("removeAllEvents");
        Project instance = new Project();
        instance.removeAllEvents();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAllDevelopers method, of class Project.
     */
    @Test
    public void testRemoveAllDevelopers() {
        System.out.println("removeAllDevelopers");
        Project instance = new Project();
        instance.removeAllDevelopers();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enableBonus method, of class Project.
     */
    @Test
    public void testEnableBonus() {
        System.out.println("enableBonus");
        Project instance = new Project();
        instance.enableBonus();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addFunctionalArea method, of class Project.
     */
    @Test
    public void testAddFunctionalArea() {
        System.out.println("addFunctionalArea");
        FunctionalArea area = null;
        Project instance = new Project();
        instance.addFunctionalArea(area);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearEvents method, of class Project.
     */
    @Test
    public void testClearEvents() {
        System.out.println("clearEvents");
        Project instance = new Project();
        instance.clearEvents();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
