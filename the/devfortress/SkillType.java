/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package the.devfortress;

/**
 *
 * @author PC
 */
public enum SkillType {
    TECHNICAL("Technical Skills"),
    META("Meta Skills"),
    PERSONAL("Personal Skills"),
    CONFIGMANA("Configuration Management Skills");
    
    private String type;

    private SkillType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
