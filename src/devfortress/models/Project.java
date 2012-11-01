/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models;

import devfortress.enumerations.AreaName;
import devfortress.exceptions.DeveloperBusyException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Team Poseidon
 */
public class Project {

    private int duration, level, budget, bonus;
    private LinkedList<Developer> developers;
    private LinkedList<FunctionalArea> areas;
    private LinkedList<Event> events;

    public Project(int duration, int level, LinkedList<FunctionalArea> areas) {
        this.duration = duration;
        this.level = level;
        this.areas = areas;
        this.developers = new LinkedList<Developer>();
        this.areas = new LinkedList<FunctionalArea>();
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

    public LinkedList<FunctionalArea> getAreas() {
        return areas;
    }

    public LinkedList<Developer> getDevelopers() {
        return developers;
    }

    public LinkedList<Event> getEvents() {
        return events;
    }

    /* Developers are added when project is created */
    public void addDeveloper(Developer dev) throws DeveloperBusyException {
        dev.acceptProject(this);
        developers.add(dev);
    }

    /* Developers can be removed after each turn */
    public void removeDeveloper(Developer dev) {
        Iterator<Developer> itr = developers.iterator();
        while (itr.hasNext()) {
            Developer d = itr.next();
            if (d == dev) {
                d.leaveProject();
                itr.remove();
                break;
            }
        }
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
        for (Developer d : developers) {
            d.leaveProject();
        }
        developers.clear();
    }

    /* Private methods and functions */
    private void calculateBudget() {
        budget = level * duration * 300;
        bonus = 0;
    }
}