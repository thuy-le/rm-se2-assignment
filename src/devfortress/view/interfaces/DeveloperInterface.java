/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view.interfaces;

import devfortress.models.Developer;
import java.awt.event.MouseListener;
import javax.swing.DefaultListModel;

/**
 *
 * @author PC
 */
public interface DeveloperInterface {

    Developer getSelectedDeveloper();

    int getSelectedIndex();
    
    DefaultListModel getDevModel();

    void setSelectedDeveloper(int index);

    void addHireDevListener(MouseListener l);

    void addFeedAllDevListener(MouseListener l);

    void addPartyListener(MouseListener l);

    void addFeedSelectedDevListener(MouseListener l);

    void addGiveBeerListener(MouseListener l);

    void addFireDevListener(MouseListener l);

    void addTrainDeveloperListener(MouseListener l);

    void showDeveloper(Developer dev);
}
