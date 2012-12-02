/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models.effects;

import devfortress.enumerations.AreaName;
import devfortress.models.Effect;
import devfortress.models.FunctionalArea;
import devfortress.models.Project;
import devfortress.utilities.Utilities;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Team Poseidon
 */
public class IdioticMarketing extends Effect {

    @Override
    public void takeEffect(Project project) {
        //Get a list of available areas
        List<FunctionalArea> pAreas = new LinkedList<FunctionalArea>(project.getAreas().values());
        List<AreaName> areas = new LinkedList<AreaName>(Arrays.asList(AreaName.values()));
        for (FunctionalArea area : pAreas) {
            areas.remove(area.getName());
        }
        int points = pAreas.get(Utilities.randInt(pAreas.size())).getFunctionPoints();
        points = (int) (((double) points) * (1 + Math.random() / 10));
        //Generate a random functional area
        FunctionalArea area = Utilities.getRandomFunctionalArea(areas, points, true);
        if (area != null) {
            project.addFunctionalArea(area);
            // Set descripttion
            description = "Requirement \"" + area.getName().toString() + "\" is added to the project";
        } else {
            description = "";
        }
    }
}
