/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models.effects;

import devfortress.models.Effect;
import devfortress.models.Project;
import devfortress.utilities.EffectLevel;

/**
 *
 * @author Team Poseidon
 */
public class Bonus extends Effect {

    @Override
    public void takeEffect(Project project) {
        project.enableBonus();
        if (project.getBonus() == 0) {
            description = "";
            effectLevel = EffectLevel.NEUTRAL;
        } else {
            description = "Project " + project.getName() + " has been given $" + project.getBonus() + " bonus for early completion";
            effectLevel = EffectLevel.POSITIVE;
        }
    }
}
