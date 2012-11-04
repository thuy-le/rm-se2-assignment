/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models;

import devfortress.enumerations.AreaName;
import devfortress.exceptions.DeveloperBusyException;
import devfortress.exceptions.InvalidFunctionalAreaException;
import devfortress.utilities.ReadOnlyList;
import devfortress.utilities.ReadOnlyMap;
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
    private List<Event> events;

    public Project(int duration, int level, EnumMap<AreaName, FunctionalArea> areas) {
        this.duration = duration;
        this.level = level;
        this.areas = areas;
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

    public Map<AreaName, FunctionalArea> getAreas() {
        return new ReadOnlyMap<AreaName, FunctionalArea>(areas);
    }

    public List<Developer> getDevelopers() {
        List<Developer> devs = new LinkedList<Developer>();
        /*$$$ Need elaboration*/
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
    }

    public void removeFunctionalArea(AreaName area) {
    }

    public void reduceFunctionalPoints(AreaName area, int points) {
    }

    public void addEvent(Event event) {
    }

    /* Loop through all events and call take effect from each events */
    public void proceedEvents() {
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
}