/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view;

import devfortress.utilities.Colour;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JToolTip;

/**
 *
 * @author PC
 */
public class CustomLabel extends JLabel {

    public CustomLabel(Icon image) {
        super(image);
    }

    
    @Override
    public JToolTip createToolTip() {
        JToolTip tooltip = super.createToolTip();
        tooltip.setFont(new Font("Century Gothic", Font.BOLD, 16));
        tooltip.setForeground(Colour.DARKBLUE);
        tooltip.setBorder(BorderFactory.createLineBorder(Colour.DARKBLUE, 1));
        tooltip.setBackground(Colour.LIGHTBLUE);
        return tooltip;
    }
}
