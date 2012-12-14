/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models.effects;

import devfortress.models.Developer;
import devfortress.models.Effect;
import devfortress.models.Project;
import devfortress.utilities.EffectLevel;
import java.util.List;

/**
 *
 * @author Team Poseidon
 */
public class Hacked extends Effect {

    @Override
    public void takeEffect(Project project) {
        List<Developer> devs = project.getDevelopers();
        // Developers do not produce any function points
        for (Developer dev : devs) {
            dev.setLastWeekFunctionPoints(0);
        }
        //Set description
        description = "System was hacked. All developers do not produce anything this week.";
        effect = EffectLevel.NEGATIVE;
    }
}
