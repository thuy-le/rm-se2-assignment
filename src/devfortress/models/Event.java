package devfortress.models;

/**
 *
 * @author Team Poseidon
 */
public class Event {

    private Effect implementor;
    private Project project;

    public Event(Effect implementor, Project project) {
        this.implementor = implementor;
        this.project = project;
    }

    public Effect getImplementor() {
        return implementor;
    }

    public String getDescription() {
        return implementor.getEventDescription();
    }

    public void takeEffect() {
        implementor.takeEffect(project);
    }
}