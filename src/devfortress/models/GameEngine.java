/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models;

import devfortress.enumerations.SkillInfo;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Team Poseidon
 */
public class GameEngine {

    private int budget, year, month, week;
    private List<Developer> developers, marketDevelopers;
    private List<Project> projects, marketProjects;
    private static GameEngine instance;

    private GameEngine(int budget) {
        this.budget = budget;
        this.developers = new LinkedList<Developer>();
        this.projects = new LinkedList<Project>();
        this.marketProjects = new LinkedList<Project>();
        this.marketDevelopers = new LinkedList<Developer>();
    }

    public static void initialize(int budget) {
        if (instance != null) {
            instance = new GameEngine(budget);
        }
    }

    public static GameEngine getInstance() {
        if (instance != null) {
            return instance;
        } else {
            initialize(1000);
            return instance;
        }
    }

    /* Manage Developers */
    public List<Developer> getAllDevelopers() {
        return developers;
    }

    public List<Developer> getMarketDevelopers() {
        return marketDevelopers;
    }

    public void hireDeveloper(Developer dev) {
    }

    public void fireDeveloper(Developer dev) {
    }

    public void feedDeveloper(Developer dev) {
    }

    public void giveDeveloperBeer(Developer dev) {
    }

    public void trainDeveloper(Developer dev, SkillInfo skill) {
    }

    /* Manage Projects */
    public List<Project> getAllProjects() {
        return projects;
    }

    public List<Project> getMarketProjects() {
        return marketProjects;
    }

    public void cancelProject(Project project) {
    }

    public void acceptProject(Project project) {
    }

    public void receiveMoney(Project project) {
    }

    public void assignDeveloperToProject(Developer dev, Project pro) {
    }

    public void removeDeveloperFromProject(Developer dev, Project pro) {
    }
}
