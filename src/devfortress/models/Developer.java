/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models;

import devfortress.exceptions.WorkingProjectException;
import devfortress.enumerations.SkillInfo;
import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 *
 * @author PC
 */
public class Developer {

    private String name;
    private TreeMap<SkillInfo, Skill> skills;
    private SkillInfo mainSkill;
    private boolean happy, fed, drunk;
    private int salary;
    private Project workingProject;

    public Developer(String name) {
        this.name = name;
        this.skills = new TreeMap<SkillInfo, Skill>();
        this.mainSkill = null;
        this.salary = 0;
        this.workingProject = null;
    }
    /* Getters */

    public boolean isHappy() {
        return happy;
    }

    public boolean isFed() {
        return fed;
    }

    public boolean isDrunk() {
        return drunk;
    }

    public SkillInfo getMainSkill() {
        return mainSkill;
    }

    public Project getWorkingProject() {
        return workingProject;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public TreeMap<SkillInfo, Skill> getSkills() {
        return skills;
    }

    /* Setters */
    public void setHappy(boolean happy) {
        this.happy = happy;
    }
    /* Everyweek, have to feed developers */

    public void feed() {
        this.fed = true;
    }
    /* Give him some beers */

    public void drink() {
        this.drunk = true;
        this.happy = true;
    }
    /* This method is called every week */

    public void getTired() {
        double rand = Math.random();
        if (rand < 0.4) {
            happy = false;
        }
        fed = false;
    }

    /* Events can make developers unhappy */
    public void getUnhappy() {
        happy = false;
    }

    /* Used when initialize a new developer or train a new skill */
    public void addSkill(Skill skill) {
        skills.put(skill.getSkillInfo(), skill);
        re_calculateDeveloperInfo();
    }

    /* NOT FINISHED!!! Null Pointer Exception*/
    public void trainSkill(SkillInfo skillInfo) {
        skills.get(skillInfo).levelUp();
        re_calculateDeveloperInfo();
    }

    /* This function is called by project */
    public void acceptProject(Project project) throws WorkingProjectException {
        workingProject = project;
    }

    /* This function is called by project */
    public void leaveProject() {
        workingProject = null;
    }

    /*Private methods */
    private void re_calculateDeveloperInfo() {
        determineMainSkill();
        calculateSalary();
    }

    private void determineMainSkill() {
        LinkedList<Skill> skillList = new LinkedList<Skill>();
        for (SkillInfo key : skills.keySet()) {
            skillList.add(skills.get(key));
        }
        Collections.sort(skillList);
        mainSkill = skillList.get(0).getSkillInfo();
    }

    private void calculateSalary() {
        int totalPoint = 0;
        for (SkillInfo key : skills.keySet()) {
            totalPoint += skills.get(key).getLevel();
        }
        if (mainSkill != null) {
            totalPoint += skills.get(mainSkill).getLevel();
        }
        this.salary = totalPoint * 10;
    }
}