package devfortress.models;

import devfortress.enumerations.SkillInfo;
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

    @Before
    public void setUp() {
        testObject = new Developer();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of determineMainSkill method, of class Developer.
     * <p>Actually, this is the test of determineMainSkill() method.</p>
     * Case 1: developer with no skill.
     * @deprecated the developer's skill have been randomized at the constructor
     */
    @Test
    public void testDetermineMainSkill_1() {
        SkillInfo expResult = null;
        SkillInfo result = testObject.getMainSkill();
        assertEquals(expResult, result);
    }

    /**
     * Test of determineMainSkill method, of class Developer.
     * <p>Actually, this is the test of determineMainSkill() method.</p>
     * Case 2: developer with 1 technical skill.
     * @deprecated the developer's skill have been randomized at the constructor
     */
    @Test
    public void testDetermineMainSkill_2() {
        Skill testSkill = new Skill(10, SkillInfo.JAVA);
        testObject.addSkill(testSkill);
        SkillInfo expResult = testSkill.getSkillInfo();
        SkillInfo result = testObject.getMainSkill();
        assertEquals(expResult, result);
    }

    /**
     * Test of DetermineMainSkill method, of class Developer.
     * <p>Actually, this is the test of determineMainSkill() method.</p>
     * Case 3: developer with 2 skills, 1 technical skill and 1 communication skill.
     */
    @Test
    public void testDetermineMainSkill_3() {
        Skill techSkill = new Skill(1, SkillInfo.JAVA);
        Skill comSkill = new Skill(4, SkillInfo.COMMUNICATION);
        testObject.addSkill(comSkill);
        testObject.addSkill(techSkill);
        SkillInfo expResult = techSkill.getSkillInfo();
        SkillInfo result = testObject.getMainSkill();
        assertEquals(expResult, result);
    }

    /**
     * Test of DetermineMainSkill method, of class Developer.
     * <p>Actually, this is the test of determineMainSkill() method.</p>
     * Case 4: developer with 2 skills, both technical.
     */
    @Test
    public void testDetermineMainSkill_4() {
        Skill techSkill1 = new Skill(1, SkillInfo.JAVA);
        Skill techSkill2 = new Skill(4, SkillInfo.C);
        testObject.addSkill(techSkill1);
        testObject.addSkill(techSkill2);
        SkillInfo expResult = techSkill2.getSkillInfo();
        SkillInfo result = testObject.getMainSkill();
        assertEquals(expResult, result);
    }

    /**
     * Test of DetermineMainSkill method, of class Developer.
     * <p>Actually, this is the test of determineMainSkill() method.</p>
     * Case 5: developer with 2 technical skills with the same level.
     */
    @Test
    public void testDetermineMainSkill_5() {
        Skill techSkill1 = new Skill(4, SkillInfo.JAVA);
        Skill techSkill2 = new Skill(4, SkillInfo.C);
        testObject.addSkill(techSkill1);
        testObject.addSkill(techSkill2);
        SkillInfo expResult = techSkill2.getSkillInfo();
        SkillInfo result = testObject.getMainSkill();
        assertEquals(expResult, result);
    }

    /**
     * Test of DetermineMainSkill method, of class Developer.
     * <p>Actually, this is the test of determineMainSkill() method.</p>
     * Case 6: developer no technical skill.
     */
    @Test
    public void testDetermineMainSkill_6() {
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
     * Test of trainSkill method, of class Developer.
     * Simple test.
     */
    @Test
    public void testTrainSkill_1() {
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
    public void testTrainSkill_2() {
        SkillInfo testSkillInfo = SkillInfo.C;
        testObject.addSkill(new Skill(10, testSkillInfo));
        testObject.trainSkill(testSkillInfo);
        int expResult = 10;
        assertEquals(expResult, testObject.getSkills().get(testSkillInfo).getLevel());
    }
}
