package devfortress.models;

import devfortress.enumerations.AreaName;
import devfortress.enumerations.Expenses;
import devfortress.enumerations.Options;
import devfortress.enumerations.SkillInfo;
import devfortress.models.exceptions.*;
import devfortress.utilities.ReadOnlyList;
import devfortress.utilities.Utilities;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author Team Poseidon
 */
public class GameEngine extends Observable {

    private static final int DEFAULT_BUDGET = 20000;
    private int budget;
    private DevDate date;
    private List<Developer> developers, developers_ReadOnly, marketDevelopers, marketDevelopers_ReadOnly;
    private List<Project> projects, projects_ReadOnly, marketProjects, marketProject_ReadOnly, pastProjects, pastProjects_ReadOnly;
    private List<String> projectNotifications;
    private static GameEngine instance = new GameEngine();
    private String playerName;
    private int numPCs;
    private boolean ended;
    private Options feedOption, beerOption;
    //Some variables for end game report:
    private int numHiredDevs;

    private GameEngine() {
        this.budget = 10000;
        this.developers = new LinkedList<Developer>();
        this.projects = new LinkedList<Project>();
        this.marketProjects = new LinkedList<Project>();
        this.marketDevelopers = new LinkedList<Developer>();
        this.pastProjects = new LinkedList<Project>();
        this.developers_ReadOnly = new ReadOnlyList<Developer>(developers);
        this.projects_ReadOnly = new ReadOnlyList<Project>(projects);
        this.marketProject_ReadOnly = new ReadOnlyList<Project>(marketProjects);
        this.marketDevelopers_ReadOnly = new ReadOnlyList<Developer>(marketDevelopers);
        this.pastProjects_ReadOnly = new ReadOnlyList<Project>(pastProjects);
        this.projectNotifications = new ArrayList<String>();
        this.feedOption = Options.MANUALLY_FEED_DEVS;
        this.beerOption = Options.MANUALLY_GIVE_BEER;
        this.date = new DevDate();
        this.playerName = "";
        this.ended = false;
        this.numHiredDevs = 0;
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
        this.developers.clear();
        this.projects.clear();
        this.date.reset();
        this.numPCs = 0;
        this.ended = false;
        this.generateRandomMarketDevelopers();
        this.generateRandomMarketProjects();
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

    public boolean hasAvalableDevs() {
        if (developers.isEmpty()) {
            return false;
        }
        for (Developer dev : developers) {
            if (dev.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    /*
     * Manage Developers
     */
    public List<Developer> getDevelopers() {
        return developers_ReadOnly;
    }

    public List<Developer> getMarketDevelopers() {
        return marketDevelopers_ReadOnly;
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
            budget -= cost;
            if (cost > 0) {
                numPCs++;
            }
        }
        numHiredDevs++;
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
            setChanged();
            throw new InsufficientBudgetException();
        }

    }

    public void setOptions(Options feedOption, Options beerOption) {
        this.feedOption = feedOption;
        this.beerOption = beerOption;
    }

    public int getFeedingExpense() {
        return (Expenses.PIZZA.getCost() + Expenses.COFFEE.getCost()) * 5 + Expenses.REDBULL.getCost();
    }

    public int getBeerExpense() {
        return Expenses.BEER.getCost();
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
            setChanged();
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
        return projects_ReadOnly;
    }

    /**
     * @return a list of new projects available to accept
     */
    public List<Project> getMarketProjects() {
        return marketProject_ReadOnly;
    }

    /**
     * @return a list of past projects which the player had finished in the game
     */
    public List<Project> getPastProjects() {
        return pastProjects_ReadOnly;
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

    public void assignDeveloperToProject(Project pro, Developer dev, AreaName area)
            throws DeveloperBusyException, InvalidFunctionalAreaException {
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
        projectNotifications.clear();
        if (!ended) {
            //Things to do every week
            generateRandomEvents();
            allEventsTakeEffects();
            allProjectProgress();
            for (Developer dev : developers) {
                dev.getTired();
            }
            if (feedOption == Options.KEEP_ALL_DEVS_FULL) {
                for (Developer dev : developers) {
                    try {
                        if (!dev.isFed()) {
                            feedDeveloper(dev);
                        }
                    } catch (InsufficientBudgetException ex) {
                        throw new GameOverException();
                    }
                }
            } else if (feedOption == Options.KEEP_WORKING_DEVS_FULL) {
                for (Developer dev : developers) {
                    try {
                        if (!dev.isAvailable()) {
                            if (!dev.isFed()) {
                                feedDeveloper(dev);
                            }
                        }
                    } catch (InsufficientBudgetException ex) {
                        throw new GameOverException();
                    }
                }
            }
            if (beerOption == Options.ALL_DEVS_BEER_WEEKLY) {
                for (Developer dev : developers) {
                    try {
                        giveDeveloperBeer(dev);
                    } catch (InsufficientBudgetException ex) {
                        throw new GameOverException();
                    }
                }
            } else if (beerOption == Options.UNHAPPY_DEVS_BEER_WEEKLY) {
                for (Developer dev : developers) {
                    try {
                        if (!dev.isHappy()) {
                            giveDeveloperBeer(dev);
                        }
                    } catch (InsufficientBudgetException ex) {
                        throw new GameOverException();
                    }
                }
            }


            /*
             * Calculate other factors
             */
            if (date.getWeek() == 4) {
                //Things to do every month
                paySalary();
                generateRandomMarketDevelopers();
                generateRandomMarketProjects();
                randomLevelDeveloper();
                if (beerOption == Options.ALL_DEVS_BEER_MONTHLY) {
                    for (Developer dev : developers) {
                        try {
                            giveDeveloperBeer(dev);
                        } catch (InsufficientBudgetException ex) {
                            throw new GameOverException();
                        }
                    }
                } else if (beerOption == Options.UNHAPPY_DEVS_BEER_MONTHLY) {
                    for (Developer dev : developers) {
                        try {
                            if (!dev.isHappy()) {
                                giveDeveloperBeer(dev);
                            }
                        } catch (InsufficientBudgetException ex) {
                            throw new GameOverException();
                        }
                    }
                }
                randomUnhappyDevLeave();
            }
            date.nextWeek();
            setChanged();
        } else {
            throw new GameOverException();
        }
    }

    public List<String> getFinishedProjects() {

        return projectNotifications;
    }

    /**
     * Save the game to the file. (Binary file)
     *
     * @param file Path of the save file
     */
    public void saveBinary(File file) throws FileNotFoundException, IOException {
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        GameMemento memento = new GameMemento(budget, date, playerName, numPCs, ended, numHiredDevs,
                developers, marketDevelopers, projects, marketProjects, pastProjects);
        System.out.println("here");
        objectOut.writeObject(memento);
        System.out.println("here2");
        objectOut.close();
        fileOut.close();
    }

    /**
     * Load the game from the file. (Binary file) <p>Reload the current instance
     * of
     * <code>{@link GameEngine}</code> with values from
     * <code>{@link GameMemento}</code> loaded from the saved file.</p>
     *
     * @param file Path of the saved file
     */
    public void loadBinary(String file) throws FileNotFoundException, IOException {
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        try {
            GameMemento memento = (GameMemento) objectIn.readObject();
            budget = memento.budget;
            playerName = memento.playerName;
            date = memento.date;
            ended = memento.ended;
            numPCs = memento.numPCs;
            numHiredDevs = memento.numHiredDevs;
            developers.clear();
            projects.clear();
            marketProjects.clear();
            pastProjects.clear();
            marketDevelopers.clear();
            developers_ReadOnly.clear();
            projects_ReadOnly.clear();
            marketProject_ReadOnly.clear();
            marketDevelopers_ReadOnly.clear();
            pastProjects_ReadOnly.clear();
            projectNotifications.clear();
            developers = memento.developers;
            projects = memento.projects;
            pastProjects = memento.pastProjects;
            marketDevelopers = memento.marketDevelopers;
            marketProjects = memento.marketProjects;
            developers_ReadOnly = new ReadOnlyList<Developer>(developers);
            projects_ReadOnly = new ReadOnlyList<Project>(projects);
            marketProject_ReadOnly = new ReadOnlyList<Project>(marketProjects);
            marketDevelopers_ReadOnly = new ReadOnlyList<Developer>(marketDevelopers);
            pastProjects_ReadOnly = new ReadOnlyList<Project>(pastProjects);
        } catch (ClassNotFoundException ex) {
            System.exit(0);
        } finally {
            objectIn.close();
            fileIn.close();
        }
        setChanged();
        notifyObservers();
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
        }
    }

    private void allEventsTakeEffects() {
        for (Project p : projects) {
            p.proceedEvents();
        }
    }

    private void allProjectProgress() {
        projectNotifications.clear();
        List<Project> finishedProjects = new LinkedList<Project>();
        for (Project p : projects) {
            p.progress(date);
            if (p.isFinished()) {
                projectNotifications.add(p.getName() + " - level " + p.getLevel()
                        + " is finished. Received $" + (p.getBudget() * 3 / 4 + p.getBonus()) + "\n");
                finishedProjects.add(p);
            }
        }
        for (Project p : finishedProjects) {
            receiveMoney(p);
        }
    }

    private void randomLevelDeveloper() {
        int randInt;
        for (Project project : projects) {
            for (Developer dev : project.getDevelopers()) {
                randInt = Utilities.randInt(100);
                if (randInt < 5) {
                    dev.trainSkill(project.getMainRequirement());
                }
            }
        }
    }

    private void randomUnhappyDevLeave() {
        int randInt;
        for (Developer dev : developers) {
            if (dev != null && !dev.isHappy()) {
                randInt = Utilities.randInt(100);
                if (randInt < 8) {
                    if (dev.getWorkingProject() != null) {
                        dev.getWorkingProject().removeDeveloper(dev);
                    }
                    developers.remove(dev);
                }
            }
        }
    }

    /**
     * Save the end game report in a text file (.txt). <p>The report contain the
     * achievements the player get when playing the game such as the time
     * finished in game and completed projects.</p>
     *
     * @param reportFileName
     */
    public void endGameReport(File reportFile) {
        PrintStream reportStream = null;
        try {
            reportStream = new PrintStream(reportFile);
            reportStream.println("---------------------------------------------------\r");
            reportStream.println("Achievements in the game:\r");

            reportStream.println("Time played: " + date.getWeek() + " week "
                    + date.getMonth() + " month " + date.getYear() + "year\r");

            reportStream.println("Total hired developers: " + numHiredDevs + "\r");

            reportStream.println("Completed Project (" + pastProjects.size() + " projects): \r\n\r");
            for (Project project : pastProjects) {
                reportStream.println(project.getName() + " - Level " + project.getLevel() + "\r");
                reportStream.println("\t" + "Budget: " + project.getBudget() + "\r");
                reportStream.println("\t" + "Duration: " + project.getDuration() + " months\r");
                reportStream.println();
            }

            reportStream.println("Great work, " + playerName + "!\r");
            reportStream.println("---------------------------------------------------");
        } catch (FileNotFoundException ex) {
        } finally {
            if (reportStream != null) {
                reportStream.close();
            }
        }
    }
}
