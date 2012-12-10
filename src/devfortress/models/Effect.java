package devfortress.models;

/**
 *
 * @author Team Poseidon
 */
public abstract class Effect {

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
