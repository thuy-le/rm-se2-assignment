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
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Team Poseidon
 */
public class FeatureCut extends Effect {

    @Override
    public void takeEffect(Project project) {
        //Random one area
        List<FunctionalArea> areas = new LinkedList<FunctionalArea>(project.getAreas().values());
        for (FunctionalArea area : areas) {
            if (area.isCompleted()) {
                areas.remove(area);
            }
        }
        if (areas.isEmpty()) {
            description = "";
            effect = EffectLevel.NEUTRAL;
            return;
        }
        FunctionalArea area = areas.get(Utilities.randInt(areas.size()));
        project.removeFunctionalArea(area.getName());
        //Set description
        description = "Area \"" + area.getName().toString() + "\" is cut from the project";
        effect = EffectLevel.POSITIVE;
    }
}
