package devfortress.models;

import devfortress.enumerations.SkillInfo;

/**
 *
 * @author Team Poseidon
 */
public class Skill implements Comparable<Skill> {

    private int level;
    private SkillInfo skillInfo;
    private int nextLevelCost;

    public Skill(int level, SkillInfo skill) {
        this.level = level;
        this.skillInfo = skill;
        nextLevelCost = calculateNextLevelCost();
    }

    public Skill(SkillInfo skill) {
        this.level = 1;
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
        // The skill will be sorted in descending order
        if (this.level < s.level) {
            return -1;
        } else if (this.level > s.level) {
            return 1;
        } else {
            // But the name is sorted in ascending order
            return -this.skillInfo.getName().compareTo(s.getSkillInfo().getName());
        }
    }
}
