package devfortress.models;

import devfortress.enumerations.AreaName;
import devfortress.enumerations.SkillInfo;
import devfortress.exceptions.DeveloperBusyException;
import devfortress.exceptions.InvalidFunctionalAreaException;
import devfortress.utilities.ReadOnlyList;
import devfortress.utilities.ReadOnlyMap;
import devfortress.utilities.Utilities;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Team Poseidon
 */
public class Project {

    private int duration, level, budget, bonus;
    private Map<AreaName, FunctionalArea> areas;
    private SkillInfo mainRequirement;
    private List<Event> events;

    public Project(int duration, int level) {
        this.duration = duration;
        this.level = level;
        this.areas = new EnumMap<AreaName, FunctionalArea>(AreaName.class);
        this.events = new LinkedList<Event>();
        calculateBudget();
    }

    /* Getters */
    public int getBonus() {
        return bonus;
    }

    public int getBudget() {
        return budget;
    }

    public int getDuration() {
        return duration;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return "Project Name";
    }

    public SkillInfo getMainRequirement() {
        return mainRequirement;
    }

    public Map<AreaName, FunctionalArea> getAreas() {
        return new ReadOnlyMap<AreaName, FunctionalArea>(areas);
    }

    public List<Developer> getDevelopers() {
        List<Developer> devs = new LinkedList<Developer>();
        for (FunctionalArea area : areas.values()) {
            devs.addAll(area.getDevelopers());
        }
        return new ReadOnlyList<Developer>(devs);
    }

    public List<Event> getEvents() {
        return new ReadOnlyList<Event>(events);
    }

    /* Developers are added when project is created */
    public void addDeveloper(Developer dev, AreaName area) throws DeveloperBusyException, InvalidFunctionalAreaException {
        FunctionalArea fArea = areas.get(area);
        if (fArea == null) {
            throw new InvalidFunctionalAreaException("\"" + area + "\" is not in the project's requirements");
        }
        dev.acceptProject(this, area);
    }

    /* Developers can be removed after each turn */
    public void removeDeveloper(Developer dev) {
        areas.get(dev.getWorkingArea()).removeDeveloper(dev);
        dev.leaveProject();
    }

    public void removeFunctionalArea(AreaName area) {
        areas.remove(area);
    }

    public void reduceFunctionalPoints(AreaName area, int points) {
        areas.get(area).reducePoints(points);
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    /* Loop through all events and call take effect from each events */
    public void proceedEvents() {
        for (Event event : events) {
            event.takeEffect();
        }
    }

    public void removeAllEvents() {
        events.clear();
    }
    /* This method can be used after the project is finished,
     * or a faster way to remove developers after each turn */

    public void removeAllDevelopers() {
    }

    /* Private methods and functions */
    private void calculateBudget() {
        budget = level * duration * 300;
        bonus = 0;
    }

    private void generateRandomMarketEvents() {
    }

    private void randomize() {
        level = Utilities.randInt(6);
        int numAreas = 0;
        int numUnknown = 0;
        int fPM = (level == 6) ? (Utilities.randInt(150) + 250) : (Utilities.randInt(25) + 75);
        int totalPoints = 0;

        switch (level) {
            case 1:
                numAreas = Utilities.randInt(4) + 1;
                numUnknown = 1;
                duration = Utilities.randInt(4) + 1;
                totalPoints = duration * fPM;
                for (int i = 0; i < numAreas; i++) {
                    int distributedPoints = 0;
                }
            case 2:
                numAreas = Utilities.randInt(8) + 1;
                numUnknown = numAreas > 2 ? 2 : numAreas;
                duration = Utilities.randInt(8) + 1;
            case 3:
                numAreas = Utilities.randInt(12) + 6;
                numUnknown = 3;
                duration = Utilities.randInt(12) + 6;
            case 4:
                numAreas = Utilities.randInt(24) + 12;
                numUnknown = 4;
                duration = Utilities.randInt(24) + 12;
            case 5:
                numAreas = Utilities.randInt(30) + 5;
                numUnknown = numAreas;
                duration = Utilities.randInt(24) + 1;
        }
    }
}