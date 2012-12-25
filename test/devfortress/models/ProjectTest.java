package devfortress.models;

import devfortress.enumerations.AreaName;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
        testObject = null;
    }

    /**
     * Test of getEvents method, of class Project.
     */
    @Test
    public void testGetEvents() {
        System.out.println("getEvents");
        Project instance = new Project();
        List expResult = null;
        List result = instance.getEvents();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addDeveloper method, of class Project.
     */
    @Test
    public void testAddDeveloper() throws Exception {
        System.out.println("addDeveloper");
        Developer dev = null;
        AreaName area = null;
        Project instance = new Project();
        instance.addDeveloper(dev, area);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeDeveloper method, of class Project.
     */
    @Test
    public void testRemoveDeveloper() {
        System.out.println("removeDeveloper");
        Developer dev = null;
        Project instance = new Project();
        instance.removeDeveloper(dev);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
