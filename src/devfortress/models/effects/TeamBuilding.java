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
public class TeamBuilding extends Effect {

    @Override
    public void takeEffect(Project project) {
        List<Developer> devs = project.getDevelopers();
        for (Developer dev : devs) {
            dev.setLastWeekFunctionPoints(5);
        }
        description = "Team building! Everyone only produced 5 function points last week. However, everyone is happy now";
        effect = EffectLevel.POSITIVE;
    }
}
