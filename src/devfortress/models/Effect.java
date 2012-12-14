package devfortress.models;

import devfortress.utilities.EffectLevel;
import java.io.Serializable;

/**
 *
 * @author Team Poseidon
 */
public abstract class Effect implements Serializable {

    protected String description;
    protected GameEngine engine;
    protected int effect;

    public Effect() {
        description = "Default";
        effect = EffectLevel.NEUTRAL;
    }

    public void setEngine(GameEngine engine) {
        this.engine = engine;
    }

    public abstract void takeEffect(Project project);

    public String getEventDescription() {
        return description;
    }
    
    public int getEventEffect() {
        return effect;
    }
}
