package devfortress.models;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Team Poseidon
 */
public class Event {

    private String description;
    private EffectImplementor implementor;
    private Project project;

    public Event(EffectImplementor implementor, Project project) {
        this.implementor = implementor;
        this.project = project;
    }

    public EffectImplementor getImplementor() {
        return implementor;
    }

    public String getDescription() {
        return implementor.getEventDescription();
    }

    public void takeEffect() {
        implementor.takeEffect(project);
    }
}