package devfortress.models;

import java.io.Serializable;

/**
 *
 * @author Team Poseidon
 */
public abstract class Effect implements Serializable {

    protected String description;
    protected GameEngine engine;

    public Effect() {
        description = "Default";
    }

    public void setEngine(GameEngine engine) {
        this.engine = engine;
    }

    public abstract void takeEffect(Project project);

    public String getEventDescription() {
        return description;
    }
}
