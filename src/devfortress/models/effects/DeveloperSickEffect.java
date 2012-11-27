/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models.effects;

import devfortress.models.Developer;
import devfortress.models.EffectImplementor;
import devfortress.models.Project;
import devfortress.utilities.Utilities;
import java.util.List;

/**
 *
 * @author Team Poseidon
 */
public class DeveloperSickEffect implements EffectImplementor {

    private String description;

    @Override
    public String getEventDescription() {
        return description == null ? "" : description;
    }

    @Override
    public void takeEffect(Project project) {
        List<Developer> devs = project.getDevelopers();
        Developer dev = devs.get(Utilities.randInt(devs.size()));
        int fOut = dev.getCalculateLastWeekFunctionPoints();
        int fPoints = fOut / 2;
        dev.setLastWeekFunctionPoints(fPoints);
        description = dev.getName() + " is sick and only produce " + fPoints + " this week";
    }
}
