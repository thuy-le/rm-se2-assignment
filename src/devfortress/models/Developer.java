/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models;

import devfortress.enumerations.SkillInfo;
import java.util.TreeMap;

/**
 *
 * @author PC
 */
public class Developer {

    private String name;
    private TreeMap<SkillInfo, Skill> skills;
    private boolean happy;
    private int salary;

    public Developer(String name, TreeMap<SkillInfo, Skill> skills, int salary) {
        this.name = name;
        this.skills = skills;
        this.salary = salary;
    }

    
    
    public boolean isHappy() {
        return happy;
    }

    public void setHappy(boolean happy) {
        this.happy = happy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public TreeMap<SkillInfo, Skill> getSkills() {
        return skills;
    }

    public void setSkills(TreeMap<SkillInfo, Skill> skills) {
        if (this.skills != null && this.skills != skills) {
            this.skills.clear();
            this.skills = null;
        }
        this.skills = skills;
    }

    public void trainSkill(SkillInfo skill) {
    }
//Add skill
}
