package devfortress.models;

/**
 *
 * @author Team Poseidon
 */
public abstract class Effect {

    protected String description;

    public abstract void takeEffect(Project project);

    public String getEventDescription() {
        return description == null ? "" : description;
    }
}
