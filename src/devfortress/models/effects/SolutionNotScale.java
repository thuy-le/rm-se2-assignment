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
public class SolutionNotScale extends Effect {

    @Override
    public void takeEffect(Project project) {
        List<FunctionalArea> areas = new LinkedList<FunctionalArea>(project.getAreas().values());
        for (FunctionalArea area : areas) {
            if (area.isCompleted()) {
                areas.remove(area);
            }
        }
        FunctionalArea area = areas.get(Utilities.randInt(areas.size()));
        area.addFunctionPoints(10);
        description = "Solution does not scale. Area \"" + area.getName().toString() + "\" is added with 10 function points";
    }
}
