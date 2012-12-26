package devfortress.enumerations;

import java.io.Serializable;

/**
 *
 * @author Team Poseidon
 */
public enum SkillTypes implements Serializable {

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