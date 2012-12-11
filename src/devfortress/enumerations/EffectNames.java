package devfortress.enumerations;

/**
 *
 * @author Team Poseidon
 */
public enum EffectNames {

    DEVELOPER_SICK(10, "Function points output was halved"),
    DEVELOPER_KILLED(1, "Maximum output for each developer on project is 1. The victim and the murderer is removed"),
    REQUIREMENT_CHANGE(10, "Add 20 function points to 1 area"),
    NEW_TECH(5, "Remove 50 function points from project"),
    SOLUTION_NOT_SCALE(5, "Add 10 function points to 1 area"),
    HACKED(1, "Developers does not produce any function points this week"),
    BACKUP_FAILED(5, "Add 25 function points to 1 area"),
    HOLIDAY(10, "1 developer produces only 1 function point this week. Everyone is happy"),
    FEATURE_CUT(5, "One functional area is removed from project"),
    BONUS(1, "Additional money is given at the end of turn"),
    TEAM_BUILDING(5, "Each developer only produce 5 function points. All developers are happy"),
    REDUNDANCIES(22, "One developer is removed from team. All developers are unhappy"),
    IDIOTIC_MARKETING(10, "Additional requirement was added. Additional 10 function points was added to 1 area. 1 developer is unhappy"),
    INTERN(10, "Additional 5 function points was completed by student interns. One developer is happy"),
    NOTHING(0, "Nothing happened");
    private int chance;
    private String description;

    private EffectNames(int chance, String description) {
        this.chance = chance;
        this.description = description;
    }

    public int getChance() {
        return chance;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
