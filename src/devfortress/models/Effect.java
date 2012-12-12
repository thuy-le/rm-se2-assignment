package devfortress.models;

import java.io.Serializable;

/**
 *
 * @author Team Poseidon
 */
public abstract class Effect implements Serializable {

    private static final int NEGATIVE = -1;
    private static final int POSITIVE = 1;
    private static final int NEUTRAL = 0;
    
    protected String description;
    protected GameEngine engine;
    protected int effect;

    public Effect() {
        description = "Default";
        effect = NEUTRAL;
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
