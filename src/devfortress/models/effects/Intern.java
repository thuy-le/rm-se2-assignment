/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models.effects;

import devfortress.models.Developer;
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
public class Intern extends Effect {

    @Override
    public void takeEffect(Project project) {
        if (!project.isFinished()) {
            List<Developer> devs = project.getDevelopers();
            if (devs.isEmpty()) {
                description = "";
                effect = EffectLevel.NEUTRAL;
                return;
            }

            // 1 developer became happy
            Developer dev = devs.get(Utilities.randInt(devs.size()));
            dev.setHappy(true);
            //Random one area
            List<FunctionalArea> areas = new LinkedList<FunctionalArea>(project.getAreas().values());
            for (Iterator<FunctionalArea> itr = areas.iterator(); itr.hasNext();) {
                FunctionalArea area = itr.next();
                if (area.isCompleted()) {
                    itr.remove();
                }
            }
            FunctionalArea area = areas.get(Utilities.randInt(areas.size()));
            //5 function points completed by intern
            area.progress(5);
            //Set description
            description = "Student intern has completed 5 function points in " + area.getName().toString() + ". " + dev.getName() + " is very happy about that";
            effect = EffectLevel.POSITIVE;
        }
    }
}
