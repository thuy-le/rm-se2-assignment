/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models;

import devfortress.exceptions.GameNotInitilizedException;
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
    private String playerName, fileName;

    private GameEngine(String playerName, int budget) {
        this.budget = budget;
        this.developers = new LinkedList<Developer>();
        this.projects = new LinkedList<Project>();
        this.marketProjects = new LinkedList<Project>();
        this.marketDevelopers = new LinkedList<Developer>();
        this.year = 0;
        this.month = 1;
        this.week = 1;
        this.playerName = playerName;
        this.fileName = null;
    }

    public synchronized static void initialize(String playerName, int budget) {
        if (instance != null) {
            instance = new GameEngine(playerName, budget);
        }
    }

    public synchronized static GameEngine getInstance() throws GameNotInitilizedException {
        if (instance != null) {
            return instance;
        } else {
            throw new GameNotInitilizedException();
        }
    }

    /* Getters */
    public int getBudget() {
        return budget;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getWeek() {
        return week;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
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

    /* System */
    public void nextWeek() {
        /* Time changes */
        if (week < 3) {
            week++;
        } else {
            week = 0;
            if (month < 12) {
                month++;
            } else {
                month = 0;
                year++;
            }
        }
        /* Calculate other factors */
        /* Projects, Developers, Events... */
    }

    public void save(String file) {
    }

    public void load(String file) {
    }

    public void quit() {
        if (fileName == null || fileName.length() == 0) {
        } else {
        }
    }
}
