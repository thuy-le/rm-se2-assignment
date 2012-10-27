package devfortress.enumerations;

/**
 *
 * @author btqua_000
 */
public enum SkillTypes {

    TECHNICAL("Technical"),
    META("Meta"),
    PERSONAL("Personal"),
    CONFIGURATION_MANAGEMENT("Configuration Mnagement");
    private String name;

    private SkillTypes(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}