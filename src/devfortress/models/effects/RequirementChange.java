/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models.effects;

import devfortress.models.Effect;
import devfortress.models.FunctionalArea;
import devfortress.models.Project;
import devfortress.utilities.EffectLevel;
import devfortress.utilities.Utilities;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Team Poseidon
 */
public class RequirementChange extends Effect {

    @Override
    public void takeEffect(Project project) {
        List<FunctionalArea> areas = new LinkedList<FunctionalArea>(project.getAreas().values());
        if (areas.isEmpty()) {
            description = "";
            return;
        }
        for (Iterator<FunctionalArea> itr = areas.iterator(); itr.hasNext();) {
            FunctionalArea area = itr.next();
            if (area.isCompleted()) {
                itr.remove();
            }
        }
        FunctionalArea area = areas.get(Utilities.randInt(areas.size()));
        area.addFunctionPoints(25);
        description = "Requirement changed. Area \"" + area.getName().toString() + "\" is added with 25 function points";
        effect = EffectLevel.NEGATIVE;
    }
}
