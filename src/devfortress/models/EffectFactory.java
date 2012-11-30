package devfortress.models;

import devfortress.enumerations.EffectNames;
import devfortress.utilities.Utilities;

/**
 * Because the EventFactory does not vary, there will be no AbstractEventFactory
 * Future changes can be easily made for a fully developed AbstractFactory
 * design pattern if needed
 * @author Team Poseidon
 */
public class EffectFactory {

    private static EffectFactory instance = new EffectFactory();
    private EffectImplementor[] effects;

    private EffectFactory() {
        effects = new EffectImplementor[EffectNames.values().length];
    }

    public static EffectFactory getInstance() {
        return instance;
    }

    public EffectImplementor getRandomEffect(GameEngine model) {
        int index = Utilities.randInt(effects.length);
        return effects[index];
    }
}
