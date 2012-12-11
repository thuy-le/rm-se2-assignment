package devfortress.models;

import devfortress.enumerations.AreaName;
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

    public FunctionalArea(AreaName name, int functionPoints, int reducedPoints, boolean visible) {
        this.name = name;
        this.functionPoints = functionPoints;
        this.reducedPoints = reducedPoints;
        this.visible = visible;
        this.developers = new HashSet<Developer>();
    }

    /* Getters */
    public Set<Developer> getDevelopers() {
        return new ReadOnlySet<Developer>(developers);
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

    /* Function called every week. Called by project */
    public void progress() {
        for (Developer dev : developers) {
            int points = dev.getLastWeekFunctionPoints();
            completedPoints += points;
            completed = completed ? completed : functionPoints - reducedPoints < completedPoints;
        }
    }

    public void progress(int points) {
        completedPoints += points;
        completed = completed ? completed : functionPoints - reducedPoints < completedPoints;
    }

    public void reducePoints(int points) {
        reducedPoints += points;
    }

    /* Assume that developer is free. This function is called by Project */
    public boolean addDeveloper(Developer dev) {
        if (dev.isAvailable()) {
            return developers.add(dev);
        }
        return false;
    }

    /* Assume that developer is occupied by this Functional Area.
     * This function is called by Project */
    public boolean removeDeveloper(Developer dev) {
        return developers.remove(dev);
    }

    @Override
    public String toString() {
        return name.getName();
    }
}