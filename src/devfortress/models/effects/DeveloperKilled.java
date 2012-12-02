/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models.effects;

import devfortress.models.Developer;
import devfortress.models.Effect;
import devfortress.models.GameEngine;
import devfortress.models.Project;
import devfortress.models.exceptions.DeveloperBusyException;
import devfortress.utilities.Utilities;
import java.util.List;

/**
 *
 * @author Team Poseidon
 */
public class DeveloperKilled extends Effect {

    @Override
    public void takeEffect(Project project) {
        List<Developer> devs = project.getDevelopers();
        if (devs.size() < 2) {
            description = "";
            return;
        }
        // Random 2 developers
        int iKiller = Utilities.randInt(devs.size());
        int iVictim = Utilities.randInt(devs.size());
        while (iVictim == iKiller) {
            iVictim = Utilities.randInt(devs.size());
        }
        Developer killerDev = devs.get(iKiller);
        Developer victimDev = devs.get(iVictim);
        // Maximum output for all developers is 1
        for (Developer dev : devs) {
            dev.setLastWeekFunctionPoints(1);
        }
        // Remove the two developers
        project.removeDeveloper(killerDev);
        project.removeDeveloper(victimDev);
        //The two developers are removed from the company
        try {
            engine = GameEngine.getInstance();
            engine.fireDeveloper(killerDev);
            engine.fireDeveloper(victimDev);
        } catch (DeveloperBusyException ex) {
        }
        description = killerDev.getName() + " murdered " + victimDev.getName() + "! " + killerDev.getName() + " is arrested. R.I.P our dear " + victimDev.getName();
    }
}
