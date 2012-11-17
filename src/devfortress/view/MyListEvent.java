/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.models.Developer;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author PC
 */
public class MyListEvent implements ListSelectionListener{

    @Override
    public void valueChanged(ListSelectionEvent e) {
        //System.out.println(e.getSource().toString());
        JList list = (JList) e.getSource();
        if(list.getSelectedValue() instanceof Developer){
            Developer dev = (Developer) list.getSelectedValue();
            TabbedPaneDeveloper.getInstance().setDevName(dev.getName());
        }
    }
    
}
