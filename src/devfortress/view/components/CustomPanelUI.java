/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.view.components;

import devfortress.utilities.Colors;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.plaf.PanelUI;

/**
 *
 * @author PC
 */
public class CustomPanelUI extends PanelUI{

    private int x, y, width, height, arcH, arcW;
    private float alpha;
    private Color colour;

    public CustomPanelUI() {
        this.x = 20;
        this.y = 25;
        this.width = 755;
        this.height = 520;
        this.arcW = 20;
        this.arcH = 20;
        this.alpha = .7f;
        this.colour = Colors.YELLOW;
    }
    
    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .7f));
        g2d.setColor(colour);
        //g2d.fillRoundRect(x, y, width, height, arcW, arcH);
        super.paint(g, c);
    }
    
    
    
}
