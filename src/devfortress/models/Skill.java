package devfortress.models;

import devfortress.enumerations.SkillInfo;
import devfortress.enumerations.SkillTypes;
import java.io.Serializable;

/**
 * Developers have skills, either technical skills or other soft skills.
 * <p>- There are 4 type or skills: technical, meta, personal and configuration management</p>
 * <p>- A skill can be trained with money. Technical skills can be leveled automatically
 * when developer working on a project require that skill.</p>
 * <p>- A developer will have at least 1 technical skill at the beginning.</p>
 * <p>- Skills are used to determine developer's salary.</p>
 * @author Team Poseidon
 * @see SkillInfo
 * @see SkillTypes
 */
public class Skill implements Comparable<Skill>, Serializable {

    private int level;
    private SkillInfo skillInfo;
    private int nextLevelCost;

    public Skill(int level, SkillInfo skill) {
        this.level = (level < 0) ? 0 : (level > 10) ? 10 : level;
        this.skillInfo = skill;
        nextLevelCost = calculateNextLevelCost();
    }

    public Skill(SkillInfo skill) {
        this.level = 0;
        this.skillInfo = skill;
        nextLevelCost = calculateNextLevelCost();
    }

    public int getLevel() {
        return level;
    }

    public SkillInfo getSkillInfo() {
        return skillInfo;
    }

    public int getNextLevelCost() {
        return nextLevelCost;
    }

    public void levelUp() {
        //Level max when level == 10
        if (level < 10) {
            level++;
        }
        nextLevelCost = calculateNextLevelCost();
    }

    private int calculateNextLevelCost() {
        int cost = 0;
        if (level < 10) {
            switch (skillInfo.getType()) {
                case TECHNICAL:
                    switch (skillInfo) {
                        case LISP:
                        case HASKELL:
                        case FORTH:
                            cost = formula1(2, 4);
                            break;
                        case VHDL:
                            cost = formula1(2, 2);
                            break;
                        default:
                            cost = formula1(1, 2);
                    }
                    break;
                case META:
                    switch (skillInfo) {
                        case ALGORITHMS:
                            cost = formula2(3, 2);
                            break;
                        default:
                            cost = formula2(2, 2);
                    }
                    break;
                case PERSONAL:
                    cost = formula2(2, 2);
                    break;
                case CONFIGURATION_MANAGEMENT:
                    cost = formula1(5, 2);
                    break;
                default:
                    cost = 1;
            }
        }
        return cost;
    }

    private int formula1(int n, int m) {
        for (int i = 1; i <= this.level; i++) {
            n += m;
        }
        return n;
    }

    private int formula2(int n, int m) {
        for (int i = 1; i <= this.level; i++) {
            n *= m;
        }
        return n;
    }

    @Override
    public int compareTo(Skill s) {
        // The skills will be sorted in descending order
        if (this.level < s.level) {
            return -1;
        } else if (this.level > s.level) {
            return 1;
        }
        // But the skill name is sorted in ascending order
        return -this.skillInfo.getName().compareTo(s.getSkillInfo().getName());
    }
}
