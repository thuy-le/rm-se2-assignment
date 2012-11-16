/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view.interfaces;

import devfortress.models.Developer;
import devfortress.models.Project;

/**
 *
 * @author PC
 */
public interface SystemInterface {
    public void viewDeveloper(Developer developer);
    public void viewProject(Project project);
    public void hireNewDeveloper(Developer developer);
    public void addNewProject(Project project);
}
