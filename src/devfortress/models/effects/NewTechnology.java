/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models.effects;

import devfortress.models.Effect;
import devfortress.models.FunctionalArea;
import devfortress.models.Project;
import devfortress.utilities.Utilities;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Team Poseidon
 */
public class NewTechnology extends Effect {

    @Override
    public void takeEffect(Project project) {
        List<FunctionalArea> areas = new LinkedList<FunctionalArea>(project.getAreas().values());
        for (FunctionalArea area : areas) {
            if (area.isCompleted()) {
                areas.remove(area);
            }
        }
        int reducePoints = 50;
        description = "New technology is available. ";
        while (reducePoints > 0) {
            FunctionalArea area = areas.get(Utilities.randInt(areas.size()));
            int remaining = area.getFunctionPoints() - area.getCompletedPoints() - area.getReducedPoints();
            if (remaining > reducePoints) {
                description += reducePoints + " function points is removed from " + area.getName().toString();
                area.reducePoints(reducePoints);
                reducePoints = 0;
            } else {
                int decrease = remaining;
                area.reducePoints(decrease);
                reducePoints -= decrease;
                description += decrease + " function points is removed from " + area.getName().toString() + ". ";
            }
            areas.remove(area);
            if (areas.isEmpty()) {
                break;
            }
        }
    }
}
