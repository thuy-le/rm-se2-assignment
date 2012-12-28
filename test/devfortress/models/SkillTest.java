package devfortress.models;

import devfortress.enumerations.SkillInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for Skill class.
 * <p>The test is written using
 * <a href="http://github.com/kentback/junit/wiki">Junit 4.10</a>
 * and <a href="http://code.google.com/p/mockito/">Mockito 1.9.5</a>.</p>
 * @author Team Poseidon
 */
public class SkillTest {

    private Skill testObject;
    private SkillInfo techSkill1, techSkill2, metaSkill, personalSkill, configSkill;

    public SkillTest() {
        techSkill1 = SkillInfo.JAVA;
        techSkill2 = SkillInfo.HASKELL;
        metaSkill = SkillInfo.ALGORITHMS;
        personalSkill = SkillInfo.COMMUNICATION;
        configSkill = SkillInfo.CONFIG_MANAGEMENT;
    }

    @Before
    public void setUp() {
        testObject = null;
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of contructors of class Skill.
     * <p>Test of level initialization.</p>
     */
    @Test
    public void testContructors_level_1() {
        testObject = new Skill(techSkill1);
        int result = testObject.getLevel();
        assertEquals(0, result);
    }

    /**
     * Test of contructors of class Skill.
     * <p>Test of level initialization.</p>
     */
    @Test
    public void testContructors_level_2() {
        testObject = new Skill(0, techSkill1);
        int result = testObject.getLevel();
        assertEquals(0, result);
    }

    /**
     * Test of contructors of class Skill.
     * <p>Test of level initialization.</p>
     */
    @Test
    public void testContructors_level_3() {
        testObject = new Skill(1, techSkill1);
        int result = testObject.getLevel();
        assertEquals(1, result);
    }

    /**
     * Test of contructors of class Skill.
     * <p>Test of level initialization.</p>
     */
    @Test
    public void testContructors_level_4() {
        testObject = new Skill(-1, techSkill1);
        int result = testObject.getLevel();
        assertEquals(0, result);
    }

    /**
     * Test of contructors of class Skill.
     * <p>Test of level initialization.</p>
     */
    @Test
    public void testContructors_level_5() {
        testObject = new Skill(9, techSkill1);
        int result = testObject.getLevel();
        assertEquals(9, result);
    }

    /**
     * Test of contructors of class Skill.
     * <p>Test of level initialization.</p>
     */
    @Test
    public void testContructors_level_6() {
        testObject = new Skill(10, techSkill1);
        int result = testObject.getLevel();
        assertEquals(10, result);
    }

    /**
     * Test of contructors of class Skill.
     * <p>Test of level initialization.</p>
     */
    @Test
    public void testContructors_level_7() {
        testObject = new Skill(11, techSkill1);
        int result = testObject.getLevel();
        assertEquals(10, result);
    }

    /**
     * Test of contructors of class Skill.
     * <p>Test of SkillInfo initialization.</p>
     */
    @Test
    public void testContructors_SkillInfo_1() {
        testObject = new Skill(techSkill1);
        SkillInfo result = testObject.getSkillInfo();
        assertEquals(techSkill1, result);
    }

    /**
     * Test of contructors of class Skill.
     * <p>Test of SkillInfo initialization.</p>
     */
    @Test(expected = NullPointerException.class)
    public void testContructors_SkillInfo_2() {
        testObject = new Skill(null);
        SkillInfo result = testObject.getSkillInfo();
        assertEquals(null, result);
    }

    /**
     * Test of getNextLevelCost method, of class Skill.
     */
    @Test
    public void testGetNextLevelCost_1() {
        testObject = new Skill(0, techSkill1);
        int result = testObject.getNextLevelCost();
        assertEquals(1, result);
    }

    /**
     * Test of getNextLevelCost method, of class Skill.
     */
    @Test
    public void testGetNextLevelCost_2() {
        testObject = new Skill(1, techSkill1);
        int result = testObject.getNextLevelCost();
        assertEquals(3, result);
    }

    /**
     * Test of getNextLevelCost method, of class Skill.
     */
    @Test
    public void testGetNextLevelCost_3() {
        testObject = new Skill(9, techSkill1);
        int result = testObject.getNextLevelCost();
        assertEquals(19, result);
    }

    /**
     * Test of getNextLevelCost method, of class Skill.
     */
    @Test
    public void testGetNextLevelCost_4() {
        testObject = new Skill(0, techSkill2);
        int result = testObject.getNextLevelCost();
        assertEquals(2, result);
    }

    /**
     * Test of getNextLevelCost method, of class Skill.
     */
    @Test
    public void testGetNextLevelCost_5() {
        testObject = new Skill(1, techSkill2);
        int result = testObject.getNextLevelCost();
        assertEquals(6, result);
    }

    /**
     * Test of getNextLevelCost method, of class Skill.
     */
    @Test
    public void testGetNextLevelCost_6() {
        testObject = new Skill(5, techSkill2);
        int result = testObject.getNextLevelCost();
        assertEquals(22, result);
    }

    /**
     * Test of getNextLevelCost method, of class Skill.
     */
    @Test
    public void testGetNextLevelCost_7() {
        testObject = new Skill(0, metaSkill);
        int result = testObject.getNextLevelCost();
        assertEquals(3, result);
    }

    /**
     * Test of getNextLevelCost method, of class Skill.
     */
    @Test
    public void testGetNextLevelCost_8() {
        testObject = new Skill(1, metaSkill);
        int result = testObject.getNextLevelCost();
        assertEquals(6, result);
    }

    /**
     * Test of getNextLevelCost method, of class Skill.
     */
    @Test
    public void testGetNextLevelCost_9() {
        testObject = new Skill(5, metaSkill);
        int result = testObject.getNextLevelCost();
        assertEquals(96, result);
    }

    /**
     * Test of levelUp method, of class Skill.
     */
    @Test
    public void testLevelUp_1() {
        testObject = new Skill(0, techSkill1);
        testObject.levelUp();
        int result = testObject.getLevel();
        assertEquals(1, result);
    }

    /**
     * Test of levelUp method, of class Skill.
     */
    @Test
    public void testLevelUp_2() {
        testObject = new Skill(1, techSkill1);
        testObject.levelUp();
        int result = testObject.getLevel();
        assertEquals(2, result);
    }

    /**
     * Test of levelUp method, of class Skill.
     */
    @Test
    public void testLevelUp_3() {
        testObject = new Skill(-1, techSkill1);
        testObject.levelUp();
        int result = testObject.getLevel();
        assertEquals(1, result);
    }

    /**
     * Test of levelUp method, of class Skill.
     */
    @Test
    public void testLevelUp_4() {
        testObject = new Skill(9, techSkill1);
        testObject.levelUp();
        int result = testObject.getLevel();
        assertEquals(10, result);
    }

    /**
     * Test of levelUp method, of class Skill.
     */
    @Test
    public void testLevelUp_5() {
        testObject = new Skill(10, techSkill1);
        testObject.levelUp();
        int result = testObject.getLevel();
        assertEquals(10, result);
    }

    /**
     * Test of levelUp method, of class Skill.
     */
    @Test
    public void testLevelUp_6() {
        testObject = new Skill(11, techSkill1);
        testObject.levelUp();
        int result = testObject.getLevel();
        assertEquals(10, result);
    }

    /**
     * Test of compareTo method, of class Skill.
     */
    @Test
    public void testCompareTo_1() {
        testObject = new Skill(1, techSkill1);
        Skill s = new Skill(2, techSkill2);
        int result = testObject.compareTo(s);
        assertTrue(result < 0);
    }

    /**
     * Test of compareTo method, of class Skill.
     */
    @Test
    public void testCompareTo_2() {
        testObject = new Skill(1, techSkill1);
        Skill s = new Skill(9, techSkill2);
        int result = testObject.compareTo(s);
        assertTrue(result < 0);
    }

    /**
     * Test of compareTo method, of class Skill.
     */
    @Test
    public void testCompareTo_3() {
        testObject = new Skill(2, techSkill1);
        Skill s = new Skill(1, techSkill2);
        int result = testObject.compareTo(s);
        assertTrue(result > 0);
    }

    /**
     * Test of compareTo method, of class Skill.
     */
    @Test
    public void testCompareTo_4() {
        testObject = new Skill(2, techSkill1);
        Skill s = new Skill(2, techSkill2);
        int result = testObject.compareTo(s);
        assertTrue(result < 0);
    }
}
