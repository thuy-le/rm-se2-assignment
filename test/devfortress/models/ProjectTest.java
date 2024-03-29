package devfortress.models;

import devfortress.utilities.Utilities;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import devfortress.models.exceptions.DeveloperBusyException;
import devfortress.models.exceptions.InvalidFunctionalAreaException;
import devfortress.enumerations.AreaName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Unit test for <code>{@link Project}</code> class.
 * <p>The tests are written using
 * <a href="http://github.com/kentback/junit/wiki">Junit 4.10</a>
 * and <a href="http://code.google.com/p/mockito/">Mockito 1.9.5</a>.</p>
 * @author Team Poseidon
 */
public class ProjectTest {

    private Project testObject;

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
     * Test of removeAllEvents method, of class Project.
     */
    @Test
    public void testRemoveAllEvents() {
        System.out.println("removeAllEvents");
        testObject.addEvent(new Event(EffectFactory.getInstance().getRandomEffect(GameEngine.getInstance()), testObject));
        testObject.addEvent(new Event(EffectFactory.getInstance().getRandomEffect(GameEngine.getInstance()), testObject));
        testObject.addEvent(new Event(EffectFactory.getInstance().getRandomEffect(GameEngine.getInstance()), testObject));
        testObject.addEvent(new Event(EffectFactory.getInstance().getRandomEffect(GameEngine.getInstance()), testObject));
        testObject.addEvent(new Event(EffectFactory.getInstance().getRandomEffect(GameEngine.getInstance()), testObject));
        testObject.addEvent(new Event(EffectFactory.getInstance().getRandomEffect(GameEngine.getInstance()), testObject));
        testObject.addEvent(new Event(EffectFactory.getInstance().getRandomEffect(GameEngine.getInstance()), testObject));
        testObject.removeAllEvents();
        assertEquals(0, testObject.getEvents().size());
    }

    @Test
    public void testAddFunctionalArea() {
        System.out.println("addFunctionalArea");
        //Get a list of available areas
        List<FunctionalArea> pAreas = new LinkedList<FunctionalArea>(testObject.getAreas().values());
        List<AreaName> areas = new LinkedList<AreaName>(Arrays.asList(AreaName.values()));
        for (FunctionalArea area : pAreas) {
            areas.remove(area.getName());
        }
        if (areas.isEmpty()) {
            fail("Test failed due to random factor");
        } else {
            int points = pAreas.get(Utilities.randInt(pAreas.size())).getFunctionPoints();
            points = (int) (((double) points) * (1 + Math.random() / 10));
            //Generate a random functional area
            FunctionalArea area = Utilities.getRandomFunctionalArea(areas, points, true);
            testObject.addFunctionalArea(area);
            assertEquals(area, testObject.getAreas().get(area.getName()));
        }
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

    @Test
    public void testRemoveAllDevelopers() throws DeveloperBusyException, InvalidFunctionalAreaException {
        System.out.println("removeAllDevelopers");
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
        testObject.removeAllDevelopers();
        assertEquals(0, testObject.getDevelopers().size());
    }

    @Test
    public void testEnableBonus() {
        System.out.println("enableBonus");
        testObject.enableBonus();
        assertEquals(true, testObject.isBonused());
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
        assertEquals(dev.getCalculateLastWeekFunctionPoints(), area.getCompletedPoints());
    }

    /**
     * Test of progress method, of class Project.
     * <p>With 1 developer on one functional area until the functional area is completed.</p>
     */
    @Test
    public void testProgress_3() throws DeveloperBusyException, InvalidFunctionalAreaException {
        DevDate date = mock(DevDate.class);
        when(date.getWeek()).thenReturn(2).thenReturn(3).thenReturn(4).thenReturn(1);

        Developer dev = mock(Developer.class);
        when(dev.getLastWeekFunctionPoints()).thenReturn(10);

        FunctionalArea area = (FunctionalArea) testObject.getAreas().values().toArray()[0];
        testObject.addDeveloper(dev, area.getName());
        while (!area.isCompleted()) {
            testObject.progress(date);
        }
        assertEquals(0, testObject.getDevelopers().size());
    }
}
