package devfortress.models;

import devfortress.enumerations.AreaName;
import devfortress.enumerations.SkillInfo;
import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit test for <code>{@link Developer}</code>.
 * @author Tang Vinh Thanh <s3245715@rmit.edu.vn>
 */
public class DeveloperTest {
    
    Developer testObject = null;
    
    public DeveloperTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        testObject = new Developer("test developer");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isHappy method, of class Developer.
     */
    @Ignore
    @Test
    public void testIsHappy() {
        System.out.println("isHappy");
        Developer instance = null;
        boolean expResult = false;
        boolean result = instance.isHappy();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isFed method, of class Developer.
     */
    @Ignore
    @Test
    public void testIsFed() {
        System.out.println("isFed");
        Developer instance = null;
        boolean expResult = false;
        boolean result = instance.isFed();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDrunk method, of class Developer.
     */
    @Ignore
    @Test
    public void testIsDrunk() {
        System.out.println("isDrunk");
        Developer instance = null;
        boolean expResult = false;
        boolean result = instance.isDrunk();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAvailable method, of class Developer.
     */
    @Ignore
    @Test
    public void testIsAvailable() {
        System.out.println("isAvailable");
        Developer instance = null;
        boolean expResult = false;
        boolean result = instance.isAvailable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMainSkill method, of class Developer.
     * <p>Actually, this is the test of determineMainSkill() method.</p>
     * Case 1: developer with no skill.
     */
    @Test
    public void testGetMainSkill1() {
        SkillInfo expResult = null;
        SkillInfo result = testObject.getMainSkill();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMainSkill method, of class Developer.
     * <p>Actually, this is the test of determineMainSkill() method.</p>
     * Case 2: developer with 1 technical skill.
     */
    @Test
    public void testGetMainSkill2() {
        Skill testSkill = new Skill(1, SkillInfo.JAVA);
        testObject.addSkill(testSkill);
        SkillInfo expResult = testSkill.getSkillInfo();
        SkillInfo result = testObject.getMainSkill();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMainSkill method, of class Developer.
     * <p>Actually, this is the test of determineMainSkill() method.</p>
     * Case 3: developer with 2 skills, 1 technical skill and 1 communication skill.
     */
    @Test
    public void testGetMainSkill3() {
        Skill techSkill = new Skill(1, SkillInfo.JAVA);
        Skill comSkill = new Skill(4, SkillInfo.COMMUNICATION);
        testObject.addSkill(comSkill);
        testObject.addSkill(techSkill);
        SkillInfo expResult = techSkill.getSkillInfo();
        SkillInfo result = testObject.getMainSkill();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMainSkill method, of class Developer.
     * <p>Actually, this is the test of determineMainSkill() method.</p>
     * Case 4: developer with 2 skills, both technical.
     */
    @Test
    public void testGetMainSkill4() {
        Skill techSkill1 = new Skill(1, SkillInfo.JAVA);
        Skill techSkill2 = new Skill(4, SkillInfo.C);
        testObject.addSkill(techSkill1);
        testObject.addSkill(techSkill2);
        SkillInfo expResult = techSkill2.getSkillInfo();
        SkillInfo result = testObject.getMainSkill();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMainSkill method, of class Developer.
     * <p>Actually, this is the test of determineMainSkill() method.</p>
     * Case 5: developer with 2 technical skills with the same level.
     */
    @Test
    public void testGetMainSkill5() {
        Skill techSkill1 = new Skill(4, SkillInfo.JAVA);
        Skill techSkill2 = new Skill(4, SkillInfo.C);
        testObject.addSkill(techSkill1);
        testObject.addSkill(techSkill2);
        SkillInfo expResult = techSkill2.getSkillInfo();
        SkillInfo result = testObject.getMainSkill();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMainSkill method, of class Developer.
     * <p>Actually, this is the test of determineMainSkill() method.</p>
     * Case 6: developer no technical skill.
     */
    @Test
    public void testGetMainSkill6() {
        Skill comSkill = new Skill(1, SkillInfo.COMMUNICATION);
        Skill metaSkill = new Skill(4, SkillInfo.DESIGN);
        Skill teamSkill = new Skill(10, SkillInfo.TEAM_PLAYER);
        testObject.addSkill(comSkill);
        testObject.addSkill(metaSkill);
        testObject.addSkill(teamSkill);
        SkillInfo expResult = null;
        SkillInfo result = testObject.getMainSkill();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWorkingArea method, of class Developer.
     */
    @Ignore
    @Test
    public void testGetWorkingArea() {
        System.out.println("getWorkingArea");
        Developer instance = null;
        AreaName expResult = null;
        AreaName result = instance.getWorkingArea();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Developer.
     */
    @Ignore
    @Test
    public void testGetName() {
        System.out.println("getName");
        Developer instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSalary method, of class Developer.
     */
    @Ignore
    @Test
    public void testGetSalary() {
        System.out.println("getSalary");
        Developer instance = null;
        int expResult = 0;
        int result = instance.getSalary();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSkills method, of class Developer.
     */
    @Ignore
    @Test
    public void testGetSkills() {
        System.out.println("getSkills");
        Developer instance = null;
        Map expResult = null;
        Map result = instance.getSkills();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHappy method, of class Developer.
     */
    @Ignore
    @Test
    public void testSetHappy() {
        System.out.println("setHappy");
        boolean happy = false;
        Developer instance = null;
        instance.setHappy(happy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of feed method, of class Developer.
     */
    @Ignore
    @Test
    public void testFeed() {
        System.out.println("feed");
        Developer instance = null;
        instance.feed();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drink method, of class Developer.
     */
    @Ignore
    @Test
    public void testDrink() {
        System.out.println("drink");
        Developer instance = null;
        instance.drink();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTired method, of class Developer.
     */
    @Ignore
    @Test
    public void testGetTired() {
        System.out.println("getTired");
        Developer instance = null;
        instance.getTired();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSkill method, of class Developer.
     */
    @Ignore
    @Test
    public void testAddSkill() {
        System.out.println("addSkill");
        Skill skill = null;
        Developer instance = null;
        instance.addSkill(skill);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of trainSkill method, of class Developer.
     * Simple test.
     */
    @Test
    public void testTrainSkill1() {
        SkillInfo testSkillInfo = SkillInfo.C;
        testObject.trainSkill(testSkillInfo);
        testObject.trainSkill(testSkillInfo);
        int expResult = 2;
        assertEquals(expResult, testObject.getSkills().get(testSkillInfo).getLevel());
    }
    
    /**
     * Test of trainSkill method, of class Developer.
     * Simple test.
     */
    @Test
    public void testTrainSkill2() {
        SkillInfo testSkillInfo = SkillInfo.C;
        testObject.addSkill(new Skill(10, testSkillInfo));
        testObject.trainSkill(testSkillInfo);
        int expResult = 10;
        assertEquals(expResult, testObject.getSkills().get(testSkillInfo).getLevel());
    }

    /**
     * Test of acceptProject method, of class Developer.
     */
    @Ignore
    @Test
    public void testAcceptProject() throws Exception {
        System.out.println("acceptProject");
        Project project = null;
        AreaName area = null;
        Developer instance = null;
        instance.acceptProject(project, area);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leaveProject method, of class Developer.
     */
    @Ignore
    @Test
    public void testLeaveProject() {
        System.out.println("leaveProject");
        Developer instance = null;
        instance.leaveProject();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
