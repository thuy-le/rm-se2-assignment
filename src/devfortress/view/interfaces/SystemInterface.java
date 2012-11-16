/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view.interfaces;

import devfortress.models.Developer;
import devfortress.models.Project;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Observer;

/**
 *
 * @author PC
 */
public interface SystemInterface extends Observer {

    void addBtnHireDevListener(MouseListener l);

    void addBtnAddProjectListener(MouseListener l);

    void addDevListListener(MouseListener l);

    void addProjectListListener(MouseListener l);

    void setPlayerName(String name);

    void setBudget(long budget);
}
