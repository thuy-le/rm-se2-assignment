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
public class FeatureCut extends Effect {

    @Override
    public void takeEffect(Project project) {
        //Random one area
        List<FunctionalArea> areas = new LinkedList<FunctionalArea>(project.getAreas().values());
        synchronized (areas) {
            for (Iterator<FunctionalArea> itr = areas.iterator(); itr.hasNext();) {
                FunctionalArea area = itr.next();
                if (area.isCompleted()) {
                    itr.remove();
                }
            }
        }

        if (areas.isEmpty()) {
            description = "";
            effectLevel = EffectLevel.NEUTRAL;
            return;
        }
        FunctionalArea area = areas.get(Utilities.randInt(areas.size()));
        project.removeFunctionalArea(area.getName());
        //Set description
        description = "Area \"" + area.getName().toString() + "\" is cut from the project";
        effectLevel = EffectLevel.POSITIVE;
    }
}
