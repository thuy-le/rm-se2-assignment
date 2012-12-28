package devfortress.models;

import devfortress.enumerations.AreaName;
import devfortress.enumerations.SkillInfo;
import devfortress.utilities.ReadOnlySet;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Team Poseidon
 */
public class FunctionalArea implements Serializable {

    private AreaName name;
    private int functionPoints;
    private int reducedPoints;
    private int completedPoints;
    private boolean visible, completed;
    private Set<Developer> developers;
    private ReadOnlySet<Developer> devs_RO;

    public FunctionalArea(AreaName name, int functionPoints, int reducedPoints, boolean visible) {
        this.name = name;
        this.functionPoints = functionPoints;
        this.reducedPoints = reducedPoints;
        this.visible = visible;
        this.developers = new HashSet<Developer>();
        this.devs_RO = new ReadOnlySet<Developer>(developers);
    }

    /*
     * Getters
     */
    public Set<Developer> getDevelopers() {
        return devs_RO;
    }

    public int getFunctionPoints() {
        return functionPoints;
    }

    public AreaName getName() {
        return name;
    }

    public int getReducedPoints() {
        return reducedPoints;
    }

    public int getCompletedPoints() {
        return completedPoints;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isCompleted() {
        return completed || functionPoints - reducedPoints < completedPoints;
    }
    //Function called by event effects

    public void addFunctionPoints(int points) {
        this.functionPoints += points;
    }

    /*
     * Function called every week. Called by project
     */
    public void progress() {
        reducedPoints = 0;
        for (Developer dev : developers) {
            addReducePoints(dev.getSkillLevel(SkillInfo.COMMUNICATION));
            int points = dev.getLastWeekFunctionPoints();
            completedPoints += points;
            completedPoints =
                    (completedPoints < functionPoints - reducedPoints)
                    ? completedPoints
                    : functionPoints - reducedPoints;
            completed = completed ? completed : functionPoints - reducedPoints <= completedPoints;
        }
    }

    public void progress(int points) {
        completedPoints += points;
        completed = completed ? completed : functionPoints - reducedPoints <= completedPoints;
    }

    public void addReducePoints(int points) {
        reducedPoints += points;
    }

    /*
     * Assume that developer is free. This function is called by Project
     */
    public boolean addDeveloper(Developer dev) {
        if (dev.isAvailable()) {
            return developers.add(dev);
        }
        return false;
    }

    /*
     * Assume that developer is occupied by this Functional Area. This function
     * is called by Project
     */
    public void removeDeveloper(Developer dev) {
        developers.remove(dev);
    }

    @Override
    public String toString() {
        return name.getName();
    }

    public void removeAllDeveloper() {
        developers.clear();
    }
}