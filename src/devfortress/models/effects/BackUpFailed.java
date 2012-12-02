/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.models.effects;

import devfortress.models.Effect;
import devfortress.models.FunctionalArea;
import devfortress.models.Project;
import devfortress.utilities.Utilities;
import java.util.ArrayList;

/**
 *
 * @author Team Poseidon
 */
public class BackUpFailed extends Effect {

    @Override
    public void takeEffect(Project project) {
        //Random one area
        ArrayList<FunctionalArea> areas = new ArrayList<FunctionalArea>(project.getAreas().values());
        int index = Utilities.randInt(areas.size());
        FunctionalArea area = areas.get(index);
        //Add 25 function points
        area.addFunctionPoints(index);
        //Set description
        description = "Add 25 function points into " + area.getName().toString();
    }
}
