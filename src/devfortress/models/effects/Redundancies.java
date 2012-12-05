/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models.effects;

import devfortress.models.Developer;
import devfortress.models.Effect;
import devfortress.models.Project;
import devfortress.utilities.Utilities;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Team Poseidon
 */
public class Redundancies extends Effect {

    @Override
    public void takeEffect(Project project) {
        List<Developer> devs = new LinkedList<Developer>(project.getDevelopers());
        if (devs.isEmpty()) {
            description = "";
            return;
        }
        Developer dev = devs.get(Utilities.randInt(devs.size()));
        project.removeDeveloper(dev);
        devs.remove(dev);
        for (Developer d : devs) {
            d.setHappy(false);
        }
        description = dev.getName() + " is removed from the project. Everyone is sad";
    }
}
