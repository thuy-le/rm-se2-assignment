/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.utilities;

import devfortress.utilities.Colour;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JToolTip;

/**
 *
 * @author PC
 */
public class CustomLabel extends JLabel {
    
    private String labelName = "";
    
    public CustomLabel(Icon image) {
        super(image);
    }

    public CustomLabel(String text) {
        super(text);
    }
    
    public void setLabelName(String labelName){
        this.labelName = labelName;
    }
    
    public String getLabelName(){
        return labelName;
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

    @Override
    public void setFont(Font font) {
        font = new Font("Century Gothic", Font.BOLD, 40);
        super.setFont(font);
    }

    @Override
    protected void paintComponent(Graphics g) {
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        super.paintComponent(g);
    }
   
    
}
