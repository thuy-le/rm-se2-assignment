package devfortress.enumerations;

/**
 *
 * @author Team Poseidon
 */
public enum EffectNames {

    DEVELOPER_SICK(10, "Function point output is halved"),
    DEVELOPER_MURDER(1, "Maximum output for developers on project is 1/developer. Dead developer and the murderer is removed");
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
