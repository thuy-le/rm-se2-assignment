package devfortress.models;

import devfortress.enumerations.AreaName;
import devfortress.enumerations.SkillInfo;
import devfortress.models.exceptions.DeveloperBusyException;
import devfortress.models.exceptions.InvalidFunctionalAreaException;
import devfortress.utilities.ReadOnlyList;
import devfortress.utilities.ReadOnlyMap;
import devfortress.utilities.Utilities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Team Poseidon
 */
public class Project implements Serializable {

    private int duration, level, budget, bonus, timeLeft;
    private boolean finished, bonused;
    private DevDate acceptedDate;
    private Map<AreaName, FunctionalArea> functionalAreas;
    private SkillInfo mainRequirement;
    private List<Event> events;
    private List<Developer> developers;
    private List<AreaName> areaNames;
    private ReadOnlyList<Developer> devs_RO;
    private ReadOnlyList<Event> events_RO;

    public Project() {
        acceptedDate = null;
        this.duration = 1;
        this.level = 1;
        this.functionalAreas = new EnumMap<AreaName, FunctionalArea>(AreaName.class);
        this.events = new LinkedList<Event>();
        this.developers = new LinkedList<Developer>();
        this.areaNames = new LinkedList<AreaName>(Arrays.asList(AreaName.values()));
        this.finished = false;
        this.devs_RO = new ReadOnlyList<Developer>(developers);
        this.events_RO = new ReadOnlyList<Event>(events);
        this.bonused = false;
        randomize();
        calculateBudget();
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
        if (bonused) {
            if (bonus == 0 && finished) {
                bonus = (int) (((float) timeLeft) / ((float) duration) * budget * ((1f + (0.1f * level)) + Utilities.randFloat() * ((float) level / 10f)));
            }
        } else {
            bonus = 0;
        }
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

    public boolean isBonused() {
        return bonused;
    }

    public Map<AreaName, FunctionalArea> getAreas() {
        return new ReadOnlyMap<AreaName, FunctionalArea>(functionalAreas);
    }

    public List<Developer> getDevelopers() {
        return devs_RO;
    }

    public List<Event> getEvents() {
        return events_RO;
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
    public synchronized void removeDeveloper(Developer dev) {
        if (functionalAreas.get(dev.getWorkingArea()) != null) {
            functionalAreas.get(dev.getWorkingArea()).removeDeveloper(dev);
            developers.remove(dev);
            dev.leaveProject();
        }


    }

    public void removeFunctionalArea(AreaName area) {
        if (functionalAreas.get(area) != null) {
            Set<Developer> devs = new HashSet<Developer>(functionalAreas.get(area).getDevelopers());
            synchronized (devs) {
                Iterator<Developer> itr = devs.iterator();
                for (; itr.hasNext();) {
                    removeDeveloper(itr.next());
                }
                functionalAreas.remove(area);
            }
        }
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
        for (FunctionalArea area : functionalAreas.values()) {
            area.progress();
            if (area.isCompleted()) {
                area.removeAllDeveloper();
            }
            if (finished == true && !area.isCompleted()) {
                finished = false;
            }
        }
        for (Developer dev : devs_RO) {
            if (dev.getMainSkill() != mainRequirement) {
                dev.setHappy(false);
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
        //All developer leaves projects
        for (Developer dev : developers) {
            dev.leaveProject();
        }
        for (FunctionalArea area : functionalAreas.values()) {
            area.removeAllDeveloper();
        }
        functionalAreas.clear();
        developers.clear();
    }

    /*
     * Private methods and functions
     */
    private void calculateBudget() {
        float total = 0;
        for (FunctionalArea area : functionalAreas.values()) {
            total += area.getFunctionPoints();
        }
        budget = (int) (total * 12f * ((1f + (0.1f * level)) + Utilities.randFloat() * ((float) level / 10f)));
        bonus = 0;
    }

    public void enableBonus() {
        bonused = true;
    }

    private void randomize() {
        level = Utilities.randInt(5) + 1;
        int numAreas = 0;
        int numUnknown = 0;
        int pointsPerMonth = (level == 5) ? (Utilities.randInt(150) + 250) : (Utilities.randInt(30) + 70);
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
            List<AreaName> names = new ArrayList<AreaName>(areaNames);
            int i;
            for (i = 0; i < numKnown; i++) {
                FunctionalArea functionalArea = Utilities.getRandomFunctionalArea(names, pointsHolder[i], true);
                if (functionalArea != null) {
                    functionalAreas.put(functionalArea.getName(), functionalArea);
                    names.remove(functionalArea.getName());
                }
            }
            for (i = numKnown; i < numAreas; i++) {
                FunctionalArea functionalArea = Utilities.getRandomFunctionalArea(names, pointsHolder[i], false);
                if (functionalArea != null) {
                    functionalAreas.put(functionalArea.getName(), functionalArea);
                    names.remove(functionalArea.getName());
                }
            }
        }
        /**
         * ****
         */
        mainRequirement = SkillInfo.techSkills().get(Utilities.randInt(SkillInfo.techSkills().size()));
    }

    public void addFunctionalArea(FunctionalArea area) {
        functionalAreas.put(area.getName(), area);
    }
}