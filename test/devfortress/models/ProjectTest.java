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
        Project instance = new Project();
        Developer dev = mock(Developer.class);
        AreaName area = (AreaName) instance.getAreas().keySet().toArray()[0];
        instance.addDeveloper(dev, area);
        dev = mock(Developer.class);
        instance.addDeveloper(dev, area);
        dev = mock(Developer.class);
        instance.addDeveloper(dev, area);
        dev = mock(Developer.class);
        instance.addDeveloper(dev, area);
        assertEquals(instance.getDevelopers().size(), 4);
    }

    @Test
    public void testAddDeveloper2() throws Exception {
        System.out.println("addDeveloper");
        Project instance = new Project();
        Developer dev = mock(Developer.class);
        AreaName area = (AreaName) instance.getAreas().keySet().toArray()[0];
        instance.addDeveloper(dev, area);
        dev = new Developer();
        instance.addDeveloper(dev, area);
        try {
            instance.addDeveloper(dev, area);
        } catch (DeveloperBusyException developerBusyException) {
        } catch (InvalidFunctionalAreaException invalidFunctionalAreaException) {
        }
        dev = mock(Developer.class);
        instance.addDeveloper(dev, area);
        assertEquals(instance.getDevelopers().size(), 3);
    }

    @Test
    public void testAddDeveloper3() throws Exception {
        System.out.println("addDeveloper");
        Project instance = new Project();
        Developer dev = mock(Developer.class);
        AreaName area = (AreaName) instance.getAreas().keySet().toArray()[0];
        instance.addDeveloper(dev, area);
        dev = mock(Developer.class);
        instance.addDeveloper(dev, area);
        dev = mock(Developer.class);
        try {
            instance.addDeveloper(dev, null);
        } catch (DeveloperBusyException developerBusyException) {
        } catch (InvalidFunctionalAreaException invalidFunctionalAreaException) {
        }
        assertEquals(instance.getDevelopers().size(), 2);
    }

    /**
     * Test of removeDeveloper method, of class Project.
     */
    @Test
    public void testRemoveDeveloper() throws Exception {
        System.out.println("removeDeveloper");
        Project instance = new Project();
        Developer d1 = mock(Developer.class);
        AreaName area = (AreaName) instance.getAreas().keySet().toArray()[0];
        instance.addDeveloper(d1, area);
        Developer d2 = mock(Developer.class);
        instance.addDeveloper(d2, area);
        Developer d3 = mock(Developer.class);
        instance.addDeveloper(d3, area);
        Developer d4 = mock(Developer.class);
        instance.addDeveloper(d4, area);
        instance.removeDeveloper(d2);
        instance.removeDeveloper(d1);
        assertEquals(instance.getDevelopers().size(), 2);
    }

    /**
     * Test of removeFunctionalArea method, of class Project.
     */
    @Test
    public void testRemoveFunctionalArea() {
        System.out.println("removeFunctionalArea");
        AreaName area = null;
        Project instance = new Project();
        instance.removeFunctionalArea(area);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reduceFunctionalPoints method, of class Project.
     */
    @Test
    public void testReduceFunctionalPoints() {
        System.out.println("reduceFunctionalPoints");
        AreaName area = null;
        int points = 0;
        Project instance = new Project();
        instance.reduceFunctionalPoints(area, points);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
