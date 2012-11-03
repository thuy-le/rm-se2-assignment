/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models;

import devfortress.enumerations.AreaName;
import devfortress.exceptions.DeveloperBusyException;
import devfortress.enumerations.SkillInfo;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Team Poseidon
 */
public class Developer {

    private String name;
    private Map<SkillInfo, Skill> skills;
    private SkillInfo mainSkill;
    private boolean happy, fed, drunk;
    private int salary;
    private Project workingProject;
    private AreaName workingArea;

    public Developer(String name) {
        this.name = name;
        this.skills = new EnumMap<SkillInfo, Skill>(SkillInfo.class);
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

    public boolean isAvailable() {
        return workingProject == null && workingArea == null;
    }

    public SkillInfo getMainSkill() {
        return mainSkill;
    }

    public Project getWorkingProject() {
        return workingProject;
    }

    public AreaName getWorkingArea() {
        return workingArea;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public final Map<SkillInfo, Skill> getSkills() {
        return skills;
    }

    /* Setters
     * Events can make developers unhappy or happy
     */
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

    /* Used when initialize a new developer or train a new skill */
    public void addSkill(Skill skill) {
        skills.put(skill.getSkillInfo(), skill);
        re_calculateDeveloperInfo();
    }

    /* Select the skill to train */
    public void trainSkill(SkillInfo skillInfo) {
        Skill skill = skills.get(skillInfo);
        if (skill != null) {
            skills.get(skillInfo).levelUp();
        } else {
            Skill s = new Skill(1, skillInfo);
            skills.put(skillInfo, s);
        }
        re_calculateDeveloperInfo();
    }

    /* This function is only called by project.
     * Developer only accept a project when he is free
     * Upon accepting a project, he is assigned to a functional area 
     */
    public void acceptProject(Project project, AreaName area) throws DeveloperBusyException {
        if (workingProject != null) {
            if (workingProject == project) {
                throw new DeveloperBusyException("Developer has already accepted this project");
            }
            throw new DeveloperBusyException();
        }
        workingProject = project;
        workingArea = area;
    }

    /* This function is only called by project */
    public void leaveProject() {
        workingProject = null;
        workingArea = null;
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