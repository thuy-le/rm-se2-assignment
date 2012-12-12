package devfortress.models;

import devfortress.utilities.EffectLevel;
import java.io.Serializable;

/**
 *
 * @author Team Poseidon
 */
public class Event implements Serializable {

    
    
    private Effect implementor;
    private Project project;
    private String description;
    private int effect;

    public Event(Effect implementor, Project project) {
        this.implementor = implementor;
        this.project = project;
        this.description = "";
        this.effect = EffectLevel.NEUTRAL;
    }

    public Effect getImplementor() {
        return implementor;
    }

    public String getDescription() {
        return description;
    }
    
    public int getEffect(){
        return effect;
    }

    public void takeEffect() {
        if (implementor != null) {
            implementor.takeEffect(project);
            description = implementor.getEventDescription();
            effect = implementor.getEventEffect();
        } else {
            description = "";
            effect = EffectLevel.NEUTRAL;
        }
    }
}