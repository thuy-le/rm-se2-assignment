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
public class DeveloperSick extends Effect {

    @Override
    public void takeEffect(Project project) {
        List<Developer> devs = project.getDevelopers();
        if (devs.isEmpty()) {
            return;
        }
        if (devs.isEmpty()) {
            description = "";
            return;
        }
        //Who s sick
        Developer dev = devs.get(Utilities.randInt(devs.size()));
        //Calculate effect
        int fOut = dev.getCalculateLastWeekFunctionPoints();
        int fPoints = fOut / 2;
        dev.setLastWeekFunctionPoints(fPoints);
        //Set description
        description = dev.getName() + " is sick and only produces " + fPoints + " this week";
    }
}
