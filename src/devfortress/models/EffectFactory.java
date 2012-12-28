package devfortress.models;

import devfortress.enumerations.EffectNames;
import devfortress.models.effects.*;
import devfortress.utilities.Utilities;
import java.util.List;

/**
 * Because the EventFactory does not vary, there will be no AbstractEventFactory
 * Future changes can be easily made for a fully developed AbstractFactory
 * design pattern if needed
 *
 * @author Team Poseidon
 */
public class EffectFactory {

    private static EffectFactory instance;
    private Effect[] effects;
    private int[] chances;

    private EffectFactory() {
        effects = new Effect[EffectNames.values().length];
        chances = new int[100];
        int counter = 0;
        for (int i = 0; i < EffectNames.values().length; i++) {
            for (int j = 0; j < EffectNames.values()[i].getChance(); j++) {
                chances[counter] = i;
                counter++;
            }
        }
        for (int k = 0; k < 2; k++) {
            for (int i = 0; i < chances.length; i++) {
                int j = Utilities.randInt(100);
                chances[i] = chances[i] + chances[j];
                chances[j] = chances[i] - chances[j];
                chances[i] = chances[i] - chances[j];
            }
        }
        effects[0] = new DeveloperSick();
        effects[1] = new DeveloperKilled();
        effects[2] = new RequirementChange();
        effects[3] = new NewTechnology();
        effects[4] = new SolutionNotScale();
        effects[5] = new Hacked();
        effects[6] = new BackUpFailed();
        effects[7] = new Holiday();
        effects[8] = new FeatureCut();
        effects[9] = new Bonus();
        effects[10] = new TeamBuilding();
        effects[11] = new Redundancies();
        effects[12] = new IdioticMarketing();
        effects[13] = new Intern();
        effects[14] = null;
    }

    public static EffectFactory getInstance() {
        if (instance == null) {
            instance = new EffectFactory();
        }
        return instance;

    }

    public Effect getRandomEffect(GameEngine model) {
        int index = Utilities.randInt(100);
        Effect effect = effects[chances[index]];
        if (effect != null) {
            effect.setEngine(model);
        }
        if (model != null) {
            switch (EffectNames.values()[chances[index]]) {
                case DEVELOPER_KILLED:
                    List<Developer> devs = model.getDevelopers();
                    for (Developer dev : devs) {
                        if (dev.isHappy()) {
                            effect = null;
                            break;
                        }
                    }
                    break;
            }
        } else {
            switch (EffectNames.values()[chances[index]]) {
                case BONUS:
                case DEVELOPER_KILLED:
                    return getRandomEffect(model);
            }
        }
        return effect;
    }
}
