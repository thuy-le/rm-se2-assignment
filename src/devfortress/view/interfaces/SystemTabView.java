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

    void setPlayerName(String name);

    void setBudget(long budget);
    
    void setCurrentPage(int currentPage);
    
    int getPage();
    
    void refresh();
}
