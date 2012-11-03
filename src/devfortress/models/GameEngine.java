/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models;

import devfortress.exceptions.GameNotInitilizedException;
import devfortress.enumerations.SkillInfo;
import devfortress.utilities.ReadOnlyList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Team Poseidon
 */
public class GameEngine {

    private int budget;
    private DevDate date;
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
        this.date = new DevDate();
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

    public DevDate getDate() {
        return date;
    }

    /* Manage Developers */
    public List<Developer> getDevelopers() {
        return new ReadOnlyList<Developer>(developers);
    }

    public List<Developer> getMarketDevelopers() {
        return new ReadOnlyList<Developer>(marketDevelopers);
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
    public List<Project> getProjects() {
        return new ReadOnlyList<Project>(projects);
    }

    public List<Project> getMarketProjects() {
        return new ReadOnlyList<Project>(marketProjects);
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
        date.nextWeek();
        /* Calculate other factors */
        if (date.getMonth() == 1) {
            /* Projects, Developers, Events... */
        }
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
