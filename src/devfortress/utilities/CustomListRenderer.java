/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.utilities;

import devfortress.models.Developer;
import devfortress.models.Project;
import java.awt.Component;
import java.util.HashMap;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author PC
 */
public class CustomListRenderer extends DefaultListCellRenderer {

    private HashMap iconTable = new HashMap();

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
        if (value instanceof Developer) {
            Developer developer = (Developer) value;
            ImageIcon icon;
            if (developer.isHappy()) {
                icon = new ImageIcon("images/happy.png");
                iconTable.put(value, icon);
            } else {
                icon = new ImageIcon("images/unhappy.png");
                iconTable.put(value, icon);
            }
            label.setText(developer.getName());
            label.setIcon(icon);
        } else if (value instanceof Project) {
            Project prj = (Project) value;
            ImageIcon icon = new ImageIcon("images/happy.png");
            label.setText(prj.getName());
            label.setIcon(icon);
        } else {
            // Clear old icon; needed in 1st release of JDK 1.2
            label.setIcon(null);
        }
        return (label);
    }
}
