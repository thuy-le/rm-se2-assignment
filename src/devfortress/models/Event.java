package devfortress.models;

import java.io.Serializable;

/**
 *
 * @author Team Poseidon
 */
public class Event implements Serializable {

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
        if (implementor != null) {
            return implementor.getEventDescription();
        } else {
            return "Null Event";
        }
    }

    public void takeEffect() {
        if (implementor != null) {
            implementor.takeEffect(project);
        }
    }
}