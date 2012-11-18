/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.utilities;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author PC
 */
//button's custom events
public class CustomButtonEvent extends MouseAdapter {

    private Color newColour;
    private Color oldColour;

    public CustomButtonEvent(Color oldColour, Color newColour) {;
        this.newColour = newColour;
        this.oldColour = oldColour;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        if (e.getSource() instanceof CustomButton) {
            CustomButton cb = (CustomButton) e.getSource();
            if (cb.isActive()) {
                cb.setColour(newColour);
                cb.repaint();
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        if (e.getSource() instanceof CustomButton) {
            CustomButton cb = (CustomButton) e.getSource();
            if (cb.isActive()) {
                cb.setColour(oldColour);
                cb.repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        if (e.getSource() instanceof CustomButton) {
            CustomButton cb = (CustomButton) e.getSource();
            if (cb.isActive()) {
                cb.setColour(oldColour);
                cb.repaint();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseExited(e);
    }
}
