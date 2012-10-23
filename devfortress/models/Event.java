package devfortress.models;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC
 */
public class Event {
    private String name;
    private float chance;
    private int effect;
    private EffectImplementation imp;

    public float getChance() {
        return chance;
    }

    public void setChance(float chance) {
        this.chance = chance;
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public EffectImplementation getImp() {
        return imp;
    }

    public void setImp(EffectImplementation imp) {
        this.imp = imp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void takeEffect(){
        imp.takeEffect();
    }
}
