/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models;

import devfortress.exceptions.WorkingProjectException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author btqua_000
 */
public class Project {

    private LinkedList<Developer> developers;
    private int duration; //month
    private int level;
    private LinkedList<FunctionalArea> areas;
    private int budget;
    private int bonus;
    //Budget is calculated by level * duration

    public void addDeveloper(Developer dev) throws WorkingProjectException {
        dev.acceptProject(this);
    }

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

    public void removeAllDevelopers() {
        for (Developer d : developers) {
            d.leaveProject();
        }
        developers.clear();
    }
    
    public void calculateCost(){
        budget = level*duration*300;
    }
}
