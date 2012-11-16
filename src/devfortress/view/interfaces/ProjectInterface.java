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
public interface ProjectInterface {
    public void addNewProject(Project project);
    public void cancelProject(Project project);
    public void addDeveloper(Project project, Developer developer);
    public void removeDeveloper(Project project, Developer developer);
}
