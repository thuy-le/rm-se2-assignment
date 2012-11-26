/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view.interfaces;

import java.awt.event.MouseListener;

/**
 *
 * @author PC
 */
public interface SystemTabView {

    void addBtnHireDevListener(MouseListener l);

    void addBtnAddProjectListener(MouseListener l);

    void addDevListListener(MouseListener l);

    void addProjectListListener(MouseListener l);

    void setPlayerName(String name);

    void setBudget(long budget);
}
