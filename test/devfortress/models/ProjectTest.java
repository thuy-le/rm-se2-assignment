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
        testObject = null;
    }

    /**
     * Test of addDeveloper method, of class Project.
     */
    @Test
    public void testAddDeveloper_1() throws Exception {
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
    public void testAddDeveloper_2() throws Exception {
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
    public void testAddDeveloper_3() throws Exception {
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
        AreaName area = (AreaName) testObject.getAreas().keySet().toArray()[0];
        Developer d1 = mock(Developer.class);
        Developer d2 = mock(Developer.class);
        Developer d3 = mock(Developer.class);
        Developer d4 = mock(Developer.class);

        testObject.addDeveloper(d1, area);
        testObject.addDeveloper(d2, area);
        testObject.addDeveloper(d3, area);
        testObject.addDeveloper(d4, area);

        when(d1.getWorkingArea()).thenReturn(area);
        when(d2.getWorkingArea()).thenReturn(area);
        when(d3.getWorkingArea()).thenReturn(area);
        when(d4.getWorkingArea()).thenReturn(area);

        testObject.removeDeveloper(d2);
        testObject.removeDeveloper(d1);
        assertEquals(2, testObject.getDevelopers().size());
    }

    /**
     * Test of removeFunctionalArea method, of class Project.
     * <p>Remove an empty area.</p>
     */
    @Test
    public void testRemoveFunctionalArea_1() {
        int expected = testObject.getAreas().size() - 1;
        AreaName areaName = (AreaName) testObject.getAreas().keySet().toArray()[0];
        testObject.removeFunctionalArea(areaName);
        int result = testObject.getAreas().size();
        assertEquals(expected, result);
    }

    /**
     * Test of removeFunctionalArea method, of class Project.
     * <p>Remove an area that has developer work on it.</p>
     */
    @Test
    public void testRemoveFunctionalArea_2() throws DeveloperBusyException, InvalidFunctionalAreaException {
        int expected = testObject.getAreas().size() - 1;

        AreaName areaName1 = (AreaName) testObject.getAreas().keySet().toArray()[0];
        AreaName areaName2 = (AreaName) testObject.getAreas().keySet().toArray()[1];

        Developer dev1 = mock(Developer.class);
        Developer dev2 = mock(Developer.class);

        testObject.addDeveloper(dev1, areaName1);
        testObject.addDeveloper(dev2, areaName2);

        testObject.removeFunctionalArea(areaName1);
        int result = testObject.getAreas().size();
        assertEquals(expected, result);
        assertEquals(2, testObject.getDevelopers().size());
    }

    /**
     * Test of addFunctionalArea method, of class Project.
     * <p>Add mock FunctionalArea with AreaName.</p>
     */
    @Test
    public void testAddFunctionalArea_1() {
        AreaName name = AreaName.CODING;
        for (AreaName a : AreaName.values()) {
            if (!testObject.getAreas().containsKey(a)) {
                name = a;
                break;
            }
        }
        int expected = testObject.getAreas().size() + 1;

        FunctionalArea area = mock(FunctionalArea.class);
        when(area.getName()).thenReturn(name);

        testObject.addFunctionalArea(area);

        int result = testObject.getAreas().size();
        assertEquals(expected, result);
    }

    /**
     * Test of addFunctionalArea method, of class Project.
     * <p>Add mock FunctionalArea with null AreaName.</p>
     */
    @Test(expected = NullPointerException.class)
    public void testAddFunctionalArea_2() {
        AreaName name = null;

        FunctionalArea area = mock(FunctionalArea.class);
        when(area.getName()).thenReturn(name);

        testObject.addFunctionalArea(area);
    }

    /**
     * Test of progress method, of class Project.
     * <p>With no developer on the project.</p>
     */
    @Test
    public void testProgress_1() {
        DevDate date = mock(DevDate.class);
        when(date.getWeek()).thenReturn(2).thenReturn(3).thenReturn(4).thenReturn(1);
        
        testObject.progress(date);
        
        for (FunctionalArea area : testObject.getAreas().values()) {
            assertEquals(0, area.getCompletedPoints());
        }
    }
    
    /**
     * Test of progress method, of class Project.
     * <p>With 1 developer on one functional area.</p>
     */
    @Test
    public void testProgress_2() throws DeveloperBusyException, InvalidFunctionalAreaException {
        DevDate date = mock(DevDate.class);
        when(date.getWeek()).thenReturn(2).thenReturn(3).thenReturn(4).thenReturn(1);
        
        Developer dev = mock(Developer.class);
        when(dev.getLastWeekFunctionPoints()).thenReturn(10);
        
        FunctionalArea area = (FunctionalArea) testObject.getAreas().values().toArray()[0];
        testObject.addDeveloper(dev, area.getName());
        
        testObject.progress(date);
    }
}
