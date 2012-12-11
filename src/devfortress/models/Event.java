package devfortress.models;

/**
 *
 * @author Team Poseidon
 */
public class Event {

    private Effect implementor;
    private Project project;
    private String description;

    public Event(Effect implementor, Project project) {
        this.implementor = implementor;
        this.project = project;
        this.description = "";
    }

    public Effect getImplementor() {
        return implementor;
    }

    public String getDescription() {
        return description;
    }

    public void takeEffect() {
        if (implementor != null) {
            implementor.takeEffect(project);
            description = implementor.getEventDescription();
        } else {
            description = "";
        }
    }
}