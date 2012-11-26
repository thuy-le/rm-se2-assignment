/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view.interfaces;

import devfortress.models.Developer;
import devfortress.models.Project;
import java.awt.event.MouseListener;

/**
 *
 * @author PC
 */
public interface ProjectTabView {
    void addNewProjectListener(MouseListener l);
    void cancelProjectListener(MouseListener l);
    void addDevToProjectListener(MouseListener l);
    void removeDevFromProjectListener(MouseListener l);
}
