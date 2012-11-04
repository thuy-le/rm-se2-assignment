/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models;

import devfortress.enumerations.AreaName;
import devfortress.exceptions.InsufficientBudgetException;
import devfortress.enumerations.Expenses;
import devfortress.enumerations.ProjectStatus;
import devfortress.exceptions.GameNotInitilizedException;
import devfortress.enumerations.SkillInfo;
import devfortress.exceptions.DeveloperBusyException;
import devfortress.exceptions.GameAlreadyInitializedException;
import devfortress.exceptions.InvalidFunctionalAreaException;
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
    private List<Project> projects, marketProjects, pastProjects;
    private static GameEngine instance;
    private String playerName, fileName;
    private int numPCs;

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

    /* This method should only be called once */
    public static void initialize(String playerName, int budget) throws GameAlreadyInitializedException {
        synchronized (GameEngine.class) {
            if (instance != null) {
                instance = new GameEngine(playerName, budget);
            } else {
                throw new GameAlreadyInitializedException();
            }
        }
    }

    /* initialize(name,budget) need to be called first */
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

    public int getNumPCs() {
        return numPCs;
    }

    /* Manage Developers */
    public List<Developer> getDevelopers() {
        return new ReadOnlyList<Developer>(developers);
    }

    public List<Developer> getMarketDevelopers() {
        return new ReadOnlyList<Developer>(marketDevelopers);
    }

    public void hireDeveloper(Developer dev) {
        int cost = (numPCs > developers.size()) ? 0 : Expenses.PC.getCost();
        if (developers.add(dev)) {
            marketDevelopers.remove(dev);
            budget -= cost;
        }
    }

    public void fireDeveloper(Developer dev) throws DeveloperBusyException {
        if (dev.isAvailable()) {
            developers.remove(dev);
        } else {
            throw new DeveloperBusyException();
        }
    }

    public void feedDeveloper(Developer dev) throws InsufficientBudgetException {
        int cost = (Expenses.PIZZA.getCost() + Expenses.COFFEE.getCost()) * 5 + Expenses.REDBULL.getCost();
        if (budget >= cost) {
            budget -= cost;
            dev.feed();
        } else {
            throw new InsufficientBudgetException();
        }

    }

    public void giveDeveloperBeer(Developer dev) throws InsufficientBudgetException {
        int cost = Expenses.BEER.getCost();
        if (budget >= cost) {
            budget -= cost;
            dev.drink();
        } else {
            throw new InsufficientBudgetException();
        }
    }

    public void trainDeveloper(Developer dev, SkillInfo skill) throws InsufficientBudgetException {
        int cost = dev.getSkills().get(skill).getNextLevelCost();
        if (budget >= cost) {
            dev.trainSkill(skill);
            budget -= cost;
        } else {
            throw new InsufficientBudgetException();
        }
    }

    /* Manage Projects */
    public List<Project> getProjects() {
        return new ReadOnlyList<Project>(projects);
    }

    public List<Project> getMarketProjects() {
        return new ReadOnlyList<Project>(marketProjects);
    }

    public List<Project> getPastProjects() {
        return new ReadOnlyList<Project>(pastProjects);
    }

    //TODO: check end game condition
    public void cancelProject(Project project) {
        project.removeAllDevelopers();
        projects.remove(project);
        budget -= project.getBudget() / 4;
    }

    public void acceptProject(Project project) {
        projects.add(project);
        marketProjects.remove(project);
        budget += project.getBudget() / 4;
    }

    public void receiveMoney(Project project) {
        budget += project.getBudget() * 3 / 4 + project.getBonus();
        project.removeAllDevelopers();
        projects.remove(project);
        pastProjects.add(project);
    }

    public void assignDeveloperToProject(Project pro, Developer dev, AreaName area) throws DeveloperBusyException, InvalidFunctionalAreaException {
        pro.addDeveloper(dev, area);
    }

    public void removeDeveloperFromProject(Developer dev, Project pro) {
        pro.removeDeveloper(dev);
    }

    /* System */
    public void nextWeek() {
        /* Time changes */
        date.nextWeek();
        generateRandomEvents();
        allEventsTakeEffects();
        /* Calculate other factors */
        if (date.getWeek() == 1) {
            /* Projects, Developers, Events... */
            paySalary();
            generateRandomMarketDevelopers();
            generateRandomMarketProjects();
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
    /* Private methods and functions*/

    private Developer getRandomDeveloper() {
        return null;
    }

    private Event getRandomEvent() {
        return null;
    }

    private Project getRandomProject() {
        return null;
    }

    //TODO: Check game end
    private void paySalary() {
        /* $$$ Check game end */
    }

    private void generateRandomMarketDevelopers() {
    }

    private void generateRandomMarketProjects() {
    }

    private void generateRandomEvents() {
    }

    private void allEventsTakeEffects() {
    }
}
