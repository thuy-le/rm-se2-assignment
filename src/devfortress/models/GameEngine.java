package devfortress.models;

import devfortress.models.exceptions.InvalidFunctionalAreaException;
import devfortress.models.exceptions.DeveloperBusyException;
import devfortress.models.exceptions.InsufficientBudgetException;
import devfortress.models.exceptions.GameAlreadyInitializedException;
import devfortress.models.exceptions.GameOverException;
import devfortress.enumerations.AreaName;
import devfortress.enumerations.Expenses;
import devfortress.enumerations.SkillInfo;
import devfortress.utilities.ReadOnlyList;
import devfortress.utilities.Utilities;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author Team Poseidon
 */
public class GameEngine extends Observable implements Serializable {

    private static final int DEFAULT_BUDGET = 10000;
    private int budget;
    private DevDate date;
    private List<Developer> developers, marketDevelopers;
    private List<Project> projects, marketProjects, pastProjects;
    private static GameEngine instance = new GameEngine();
    private String playerName, fileName;
    private int numPCs;
    private boolean ended;

    private GameEngine() {
        this.budget = 10000;
        this.developers = new LinkedList<Developer>();
        this.projects = new LinkedList<Project>();
        this.marketProjects = new LinkedList<Project>();
        this.marketDevelopers = new LinkedList<Developer>();
        this.date = new DevDate();
        this.playerName = "";
        this.fileName = null;
        this.ended = false;
    }

    /**
     * This method should only be called once
     */
    public void initialize(String playerName, int budget) throws GameAlreadyInitializedException, IOException {
        if (this.playerName.length() > 0) {
            throw new GameAlreadyInitializedException();
        } else {
            initialize(playerName);
            this.budget = budget;
        }
    }

    public void initialize(String playerName) {
        this.playerName = playerName;
        this.budget = DEFAULT_BUDGET;
        this.generateRandomMarketDevelopers();
        this.generateRandomMarketProjects();
        developers.clear();
        projects.clear();
        date.reset();
        setChanged();
    }

    /**
     * <code>{@link #initialize(String, int)}</code> need to be called first
     */
    public synchronized static GameEngine getInstance() {
        return instance;
    }

    /*
     * Getters
     */
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

    public List<Event> getEvents() {
        List<Event> list = new LinkedList<Event>();
        for (Project project : projects) {
            list.addAll(project.getEvents());
        }
        return new ReadOnlyList<Event>(list);
    }

    /*
     * Manage Developers
     */
    public List<Developer> getDevelopers() {
        return new ReadOnlyList<Developer>(developers);
    }

    public List<Developer> getMarketDevelopers() {
        return new ReadOnlyList<Developer>(marketDevelopers);
    }

    /**
     * Hire a
     * <code>{@link Developer}</code> and purchase a new PC if needed.
     *
     * @param dev new
     * <code>{@link Developer}</code> to hire.
     */
    public void hireDeveloper(Developer dev) {
        int cost = (numPCs > developers.size()) ? 0 : Expenses.PC.getCost();
        if (developers.add(dev)) {
            marketDevelopers.remove(dev);
//            budget -= cost;
            if (cost > 0) {
                numPCs++;
            }
        }
        setChanged();
    }

    /**
     * Fire a
     * <code>{@link Developer}</code>.
     *
     * @param dev the
     * <code>{@link Developer}</code> to fire.
     * @throws DeveloperBusyException Cannot fire the developer when he is
     * working on a project.
     */
    public void fireDeveloper(Developer dev) throws DeveloperBusyException {
        if (dev.isAvailable()) {
            developers.remove(dev);
            setChanged();
        } else {
            throw new DeveloperBusyException();
        }
    }

    /**
     * Feed the
     * <code>{@link Developer}</code> with pizza, coffee and redbull to keep the
     * Developer work for 1 week.
     *
     * @param dev
     * @throws InsufficientBudgetException Cannot feed the developer if the
     * budget is insufficient
     * @see Expenses
     */
    public void feedDeveloper(Developer dev) throws InsufficientBudgetException {
        int cost = (Expenses.PIZZA.getCost() + Expenses.COFFEE.getCost()) * 5 + Expenses.REDBULL.getCost();
        if (budget >= cost) {
            budget -= cost;
            dev.feed();
            setChanged();
        } else {
            throw new InsufficientBudgetException();
        }

    }

    /**
     * Give beer to
     * <code>{@link Developer}</code> to keep he happy, but also halve his
     * productivity.
     *
     * @param dev
     * @throws InsufficientBudgetException
     * @see Expenses
     */
    public void giveDeveloperBeer(Developer dev) throws InsufficientBudgetException {
        int cost = Expenses.BEER.getCost();
        if (budget >= cost) {
            budget -= cost;
            dev.drink();
            setChanged();
        } else {
            throw new InsufficientBudgetException();
        }
    }

    /**
     * Spend money to train or upgrade a skill for one
     * <code>{@link Developer}</code>.
     *
     * @param dev
     * @param skillInfo the skill that need to be learn or upgrade
     * @throws InsufficientBudgetException
     */
    public void trainDeveloper(Developer dev, SkillInfo skillInfo) throws InsufficientBudgetException {
        Skill skill = dev.getSkills().get(skillInfo);
        if (skill == null) {
            skill = new Skill(skillInfo);
            dev.addSkill(skill);
        }
        int cost = skill.getNextLevelCost();
        if (budget >= cost) {
            dev.trainSkill(skillInfo);
            budget -= cost;
            setChanged();
        } else {
            throw new InsufficientBudgetException();
        }
    }

    // Manage Projects
    /**
     * @return a list of current working projects
     */
    public List<Project> getProjects() {
        return new ReadOnlyList<Project>(projects);
    }

    /**
     * @return a list of new projects avalable to accept
     */
    public List<Project> getMarketProjects() {
        return new ReadOnlyList<Project>(marketProjects);
    }

    /**
     * @return a list of past projects which the player had finished in the game
     */
    public List<Project> getPastProjects() {
        return new ReadOnlyList<Project>(pastProjects);
    }

    //TODO: check end game condition
    public void cancelProject(Project project) {
        project.removeAllDevelopers();
        projects.remove(project);
        budget -= project.getBudget() / 4;
        setChanged();
    }

    /**
     * Accept project and receive 25% of project budget.
     *
     * @param project
     */
    public void acceptProject(Project project) {
        projects.add(project);
        marketProjects.remove(project);
        project.setAcceptedDate(new DevDate(date));
        budget += project.getBudget() / 4;
        setChanged();
    }

    /**
     * Receive 75% of project budget when the project completed.
     *
     * @param project
     */
    public void receiveMoney(Project project) {
        budget += project.getBudget() * 3 / 4 + project.getBonus();
        project.removeAllDevelopers();
        projects.remove(project);
        pastProjects.add(project);
        setChanged();
    }

    public void assignDeveloperToProject(Project pro, Developer dev, AreaName area) throws DeveloperBusyException, InvalidFunctionalAreaException {
        pro.addDeveloper(dev, area);
        setChanged();
    }

    public void removeDeveloperFromProject(Developer dev, Project pro) {
        pro.removeDeveloper(dev);
        setChanged();
    }

    /*
     * System
     */
    public void nextWeek() throws GameOverException {
        /*
         * Time changes
         */
        if (!ended) {
            generateRandomEvents();
            allEventsTakeEffects();
            allProjectProgress();
            for (Developer dev : developers) {
                dev.getTired();
            }
            /*
             * Calculate other factors
             */
            if (date.getWeek() == 4) {
                /*
                 * Projects, Developers, Events...
                 */
                paySalary();
                generateRandomMarketDevelopers();
                generateRandomMarketProjects();
            }
            date.nextWeek();
            setChanged();
        } else {
            throw new GameOverException();
        }
    }

    /**
     * Save the game to the file. (Binary file)
     *
     * @param file Namepath of the save file
     */
    public void saveBinary(String file) throws FileNotFoundException, IOException {
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(this);
        objectOut.close();
        fileOut.close();
    }

    /**
     * Load the game from the file. (Binary file) <p>Override the current
     * instance of
     * <code>GameEngine</code> by the loaded instance from the saved file.</p>
     *
     * @param file Namepath of the saved file
     */
    public static void loadBinary(String file) throws FileNotFoundException, IOException {
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        try {
            GameEngine loadedInstance = (GameEngine) objectIn.readObject();
            instance.budget = loadedInstance.budget;
            instance.playerName = loadedInstance.playerName;
            instance.date = loadedInstance.date;
            instance.developers = loadedInstance.developers;
            instance.ended = loadedInstance.ended;
            instance.numPCs = loadedInstance.numPCs;
            instance.pastProjects = loadedInstance.pastProjects;
            
            instance.marketDevelopers = loadedInstance.marketDevelopers;
            instance.marketProjects = loadedInstance.marketProjects;
        } catch (ClassNotFoundException ex) {
        }
        objectIn.close();
        fileIn.close();
        instance.setChanged();
        instance.notifyObservers();
    }

    public void quit() {
        if (fileName == null || fileName.length() == 0) {
        } else {
        }
    }

    /*
     * Private methods and functions
     */
    //TODO: Check game end
    private void paySalary() throws GameOverException {
        /*
         * $$$ Check game end
         */
        for (Developer dev : developers) {
            budget -= dev.getSalary();
            if (budget <= 0) {
                budget = 0;
                ended = true;
                setChanged();
                throw new GameOverException();
            }
        }
    }

    private void generateRandomMarketDevelopers() {
        int num = Utilities.randInt(6) + 5;
        marketDevelopers.clear();
        for (int i = 0; i < num; i++) {
            marketDevelopers.add(new Developer());
        }
    }

    private void generateRandomMarketProjects() {
        int num = Utilities.randInt(6) + 5;
        marketProjects.clear();
        for (int i = 0; i < num; i++) {
            marketProjects.add(new Project());
        }
    }

    //TODO: Generate a list of random events
    private void generateRandomEvents() {
        try {
            EffectFactory fact = EffectFactory.getInstance();

            for (Project p : projects) {
                List<Developer> devs = p.getDevelopers();
                p.clearEvents();
                for (Developer dev : devs) {
//                    for (int i = 0; i < 5; i++) {
                    Event event = new Event(fact.getRandomEffect(this), p);
                    p.addEvent(event);
//                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void allEventsTakeEffects() {
        for (Project p : projects) {
            p.proceedEvents();
        }
    }

    private void allProjectProgress() {
        for (Project p : projects) {
            p.progress(date);
        }
    }
}
