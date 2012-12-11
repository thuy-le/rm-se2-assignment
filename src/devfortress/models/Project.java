package devfortress.models;

import devfortress.enumerations.AreaName;
import devfortress.enumerations.SkillInfo;
import devfortress.models.exceptions.DeveloperBusyException;
import devfortress.models.exceptions.InvalidFunctionalAreaException;
import devfortress.utilities.ReadOnlyList;
import devfortress.utilities.ReadOnlyMap;
import devfortress.utilities.Utilities;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Team Poseidon
 */
public class Project {

    private int duration, level, budget, bonus, timeLeft;
    private boolean finished;
    private DevDate acceptedDate;
    private Map<AreaName, FunctionalArea> functionalAreas;
    private SkillInfo mainRequirement;
    private List<Event> events;
    private List<Developer> developers;
    private LinkedList<AreaName> areaNames;

    public Project() {
        acceptedDate = null;
        this.duration = 1;
        this.level = 1;
        this.functionalAreas = new EnumMap<AreaName, FunctionalArea>(AreaName.class);
        this.events = new LinkedList<Event>();
        this.developers = new LinkedList<Developer>();
        this.areaNames = new LinkedList<AreaName>(Arrays.asList(AreaName.values()));
        this.finished = false;
        randomize();
        calculateBudget();
        for (int i = 0; i < 5; i++) {
            try {
                Developer dev = new Developer();
                GameEngine.getInstance().hireDeveloper(dev);
                FunctionalArea[] as = new FunctionalArea[functionalAreas.values().size()];
                functionalAreas.values().toArray(as);
                AreaName a = as[Utilities.randInt(as.length)].getName();
                if (functionalAreas.get(a) == null) {
                }
                addDeveloper(dev, a);
            } catch (DeveloperBusyException ex) {
            } catch (InvalidFunctionalAreaException ex) {
            }
        }
    }

    /*
     * Setter
     */
    public void setAcceptedDate(DevDate acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    /*
     * Getters
     */
    public DevDate getAcceptedDate() {
        return acceptedDate;
    }

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
        return mainRequirement.getName();
    }

    public SkillInfo getMainRequirement() {
        return mainRequirement;
    }

    public boolean isFinished() {
        return finished;
    }

    public Map<AreaName, FunctionalArea> getAreas() {
        return new ReadOnlyMap<AreaName, FunctionalArea>(functionalAreas);
    }

    public List<Developer> getDevelopers() {
        return new ReadOnlyList<Developer>(developers);
    }

    public List<Event> getEvents() {
        return new ReadOnlyList<Event>(events);
    }

    /**
     * Developers are added when project is created
     *
     * @param dev
     * @param area
     * @exception InvalidFunctionalAreaException if the area is not exist in the
     * project
     */
    public void addDeveloper(Developer dev, AreaName area) throws DeveloperBusyException, InvalidFunctionalAreaException {
        FunctionalArea fArea = functionalAreas.get(area);
        if (fArea == null) {
            throw new InvalidFunctionalAreaException("\"" + area + "\" is not in the project's requirements");
        }
        fArea.addDeveloper(dev);
        dev.acceptProject(this, area);
        developers.add(dev);
    }

    /**
     * Developers can be removed after each turn
     *
     * @param dev
     */
    public void removeDeveloper(Developer dev) {
        System.out.println("Working: " + dev.getWorkingArea());
        System.out.println("Functional Area: " + functionalAreas.get(dev.getWorkingArea()));
        functionalAreas.get(dev.getWorkingArea()).removeDeveloper(dev);
        developers.remove(dev);
        dev.leaveProject();
    }

    public synchronized void removeFunctionalArea(AreaName area) {
        Set<Developer> devs = functionalAreas.get(area).getDevelopers();
        System.out.println("REMOVE FUNCTIONAL AREA: " + area.getName() + " | " + devs.size());
        for (Developer dev : devs) {
            System.out.println("LEAVE AREA: " + dev.getName());
            removeDeveloper(dev);
        }
        functionalAreas.remove(area);

    }

    public void reduceFunctionalPoints(AreaName area, int points) {
        functionalAreas.get(area).reducePoints(points);
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Loop through all events and call take effect from each events
     */
    public void proceedEvents() {
        for (Event event : events) {
            event.takeEffect();
        }
    }

    /**
     * Advance to next week
     */
    public void progress(DevDate date) {
        finished = true;
        for (FunctionalArea areas : functionalAreas.values()) {
            areas.progress();
            if (finished == true && !areas.isCompleted()) {
                finished = false;
            }
        }
        if (date.getWeek() == 4 && timeLeft != 0) {
            timeLeft -= 1;
        }
    }

    public void removeAllEvents() {
        events.clear();
    }
    /*
     * This method can be used after the project is finished, or a faster way to
     * remove developers after each turn
     */

    public void removeAllDevelopers() {
    }

    /*
     * Private methods and functions
     */
    private void calculateBudget() {
        budget = level * duration * 300;
        bonus = 0;
    }

    public void enableBonus() {
        if (bonus == 0 && finished) {
            bonus = (int) (((float) timeLeft) / ((float) duration) * budget);
        }
    }

    private void randomize() {
        level = Utilities.randInt(5) + 1;
        int numAreas = 0;
        int numUnknown = 0;
        int pointsPerMonth = (level == 5) ? (Utilities.randInt(150) + 250) : (Utilities.randInt(25) + 75);
        int totalPoints = 0;
        int[] pointsHolder;
        // Dependencies on levels
        switch (level) {
            case 1:
                numAreas = Utilities.randInt(4) + 1;
                numUnknown = 1;
                duration = Utilities.randInt(4) + 1;
                break;
            case 2:
                numAreas = Utilities.randInt(8) + 1;
                numUnknown = numAreas > 2 ? 2 : numAreas;
                duration = Utilities.randInt(8) + 1;
                break;
            case 3:
                numAreas = Utilities.randInt(7) + 6;
                numUnknown = 3;
                duration = Utilities.randInt(7) + 6;
                break;
            case 4:
                numAreas = Utilities.randInt(13) + 12;
                numUnknown = 4;
                duration = Utilities.randInt(13) + 12;
                break;
            case 5:
                numAreas = Utilities.randInt(26) + 5;
                numUnknown = numAreas;
                duration = Utilities.randInt(24) + 1;
                break;
        }
        timeLeft = duration;
        totalPoints = duration * pointsPerMonth;
        pointsHolder = new int[numAreas];
        {
            //Fill in the array
            int division = totalPoints / numAreas;
            for (int i = 0; i < numAreas; i++) {
                pointsHolder[i] = division;
            }
            pointsHolder[Utilities.randInt(numAreas)] += totalPoints - division * numAreas;
        }
        {
            //Shuffle the array
            int margin = pointsHolder[0] / 4;
            for (int i = 0; i < numAreas; i++) {
                int exchange = Utilities.randInt(margin);
                int index = Utilities.randInt(numAreas);
                if (exchange < pointsHolder[index]) {
                    pointsHolder[i] += exchange;
                    pointsHolder[index] -= exchange;
                }
                exchange = Utilities.randInt(margin);
                index = Utilities.randInt(numAreas);
                if (exchange < pointsHolder[index]) {
                    pointsHolder[i] += exchange;
                    pointsHolder[index] -= exchange;
                }
            }
        }
        {
            int numKnown = numAreas - numUnknown;
            int i = 0;
            for (; i < numKnown; i++) {
                FunctionalArea functionalArea = Utilities.getRandomFunctionalArea(areaNames, pointsHolder[i], true);
                functionalAreas.put(functionalArea.getName(), functionalArea);
            }
            for (; i < numUnknown; i++) {
                FunctionalArea functionalArea = Utilities.getRandomFunctionalArea(areaNames, pointsHolder[i], false);
                functionalAreas.put(functionalArea.getName(), functionalArea);
            }

        }
        /**
         * ****
         */
        mainRequirement = SkillInfo.values()[Utilities.randInt(SkillInfo.values().length)];
    }

    public void addFunctionalArea(FunctionalArea area) {
        functionalAreas.put(area.getName(), area);
    }

    public void clearEvents() {
        events.clear();
    }
}