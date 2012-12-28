package devfortress.models;

import devfortress.enumerations.SkillInfo;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit test for <code>{@link Developer}</code>.
 * @author Tang Vinh Thanh <s3245715@rmit.edu.vn>
 */
public class DeveloperTest {

    public DeveloperTest() {
    }

    @Before
    public void setUp() {
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
        Developer testObject = new Developer();
        SkillInfo result = testObject.getMainSkill();
        assertNotNull(result);
    }

    /**
     * Test of determineMainSkill method, of class Developer.
     * <p>Actually, this is the test of determineMainSkill() method.</p>
     * Case 2: developer with 1 technical skill.
     * @deprecated the developer's skill have been randomized at the constructor
     */
    @Test
    public void testDetermineMainSkill_2() {
        Developer testObject = new Developer();
        Skill testSkill = new Skill(10, SkillInfo.JAVA);
        testObject.addSkill(testSkill);
        SkillInfo expResult = SkillInfo.JAVA;
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
        Developer testObject = new Developer();
        Skill testSkill = new Skill(9, SkillInfo.JAVA);
        Skill testSkill2 = new Skill(10, SkillInfo.COMMUNICATION);
        testObject.addSkill(testSkill);
        testObject.addSkill(testSkill2);
        SkillInfo expResult = SkillInfo.JAVA;
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
        Developer testObject = new Developer();
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
        Developer testObject = new Developer();
        Skill techSkill1 = new Skill(10, SkillInfo.JAVA);
        Skill techSkill2 = new Skill(10, SkillInfo.C);
        testObject.addSkill(techSkill1);
        testObject.addSkill(techSkill2);
        SkillInfo expResult = techSkill2.getSkillInfo();
        SkillInfo result = testObject.getMainSkill();
        assertEquals(expResult, result);
    }

    /**
     * Test of trainSkill method, of class Developer.
     * Simple test.
     */
    @Test
    public void testTrainSkill_1() {
        Developer testObject = new Developer();
        SkillInfo testSkillInfo = SkillInfo.C;
        int expResult = testObject.getSkillLevel(SkillInfo.C) + 2;
        testObject.trainSkill(testSkillInfo);
        testObject.trainSkill(testSkillInfo);
        assertEquals(expResult, testObject.getSkills().get(testSkillInfo).getLevel());
    }

    /**
     * Test of trainSkill method, of class Developer.
     * Simple test.
     */
    @Test
    public void testTrainSkill_2() {
        Developer testObject = new Developer();
        SkillInfo testSkillInfo = SkillInfo.C;
        testObject.addSkill(new Skill(10, testSkillInfo));
        testObject.trainSkill(testSkillInfo);
        int expResult = 10;
        assertEquals(expResult, testObject.getSkills().get(testSkillInfo).getLevel());
    }
}
