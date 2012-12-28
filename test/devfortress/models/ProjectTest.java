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
        testObject.removeAllEvents();
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

    /**
     * Test of removeAllDevelopers method, of class Project.
     */
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

    /**
     * Test of enableBonus method, of class Project.
     */
    @Test
    public void testEnableBonus() {
        System.out.println("enableBonus");
        testObject.enableBonus();
        assertEquals(true, testObject.isBonused());
    }

    /**
     * Test of addFunctionalArea method, of class Project.
     */
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
}
