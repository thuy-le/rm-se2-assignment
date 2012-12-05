/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models.effects;

import devfortress.models.Developer;
import devfortress.models.Effect;
import devfortress.models.Project;
import devfortress.utilities.Utilities;
import java.util.List;

/**
 *
 * @author Team Poseidon
 */
public class Holiday extends Effect {

    @Override
    public void takeEffect(Project project) {
        List<Developer> devs = project.getDevelopers();
        if (devs.isEmpty()) {
            description = "";
            return;
        }

        Developer dev = devs.get(Utilities.randInt(devs.size()));
        //Calculate effect
        dev.setLastWeekFunctionPoints(1);
        for (Developer d : devs) {
            d.setHappy(true);
        }
        //Set description
        description = "Holiday! Yey! Everyone is happy. However, " + dev.getName() + " only produces " + 1 + " this week.";
    }
}
