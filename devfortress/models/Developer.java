/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models;

import java.util.LinkedList;

/**
 *
 * @author PC
 */
public class Developer {
    private String name;
    private LinkedList<Skill> skills;
    private boolean isHappy;
    private int salary;

    public boolean isIsHappy() {
        return isHappy;
    }

    public void setIsHappy(boolean isHappy) {
        this.isHappy = isHappy;
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

    public LinkedList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(LinkedList<Skill> skills) {
        this.skills = skills;
    }
//Add skill
    
}
