/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view.interfaces;

import devfortress.models.Developer;

/**
 *
 * @author PC
 */
public interface DeveloperInterface {
    public void hireNewDeveloper(Developer developer);
    public void fireDeveloper(Developer developer);
    public void feedDeveloper(Developer developer);
    public void party(Developer developer);
    public void viewDeveloper(Developer developer);
    public void feedAll();
    public void partyAll();
}
