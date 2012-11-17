/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view.interfaces;

import java.awt.event.MouseListener;
import java.util.Observer;

/**
 *
 * @author PC
 */
public interface DeveloperInterface extends Observer {

    void addHireDevListener(MouseListener l);

    void addFeedAllDevListener(MouseListener l);

    void addPartyListener(MouseListener l);
}
